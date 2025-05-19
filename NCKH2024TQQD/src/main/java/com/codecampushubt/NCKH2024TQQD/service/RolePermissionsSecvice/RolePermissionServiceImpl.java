package com.codecampushubt.NCKH2024TQQD.service.RolePermissionsSecvice;

import com.codecampushubt.NCKH2024TQQD.dao.PermissionRepository;
import com.codecampushubt.NCKH2024TQQD.dao.RolePermissionRepository;
import com.codecampushubt.NCKH2024TQQD.dao.RoleRepository;
import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionAssignDTO;
import com.codecampushubt.NCKH2024TQQD.entity.Permission;
import com.codecampushubt.NCKH2024TQQD.entity.Role;
import com.codecampushubt.NCKH2024TQQD.entity.RolePermission;
import com.codecampushubt.NCKH2024TQQD.entity.RolePermissionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService{
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public List<PermissionAssignDTO> getAllRolePermissions() {
        List<RolePermission> rolePermissions = rolePermissionRepository.findAll();

        return rolePermissions.stream().map(rp -> {
            Role role = roleRepository.findById(rp.getRole().getRoleID())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            Permission permission = permissionRepository.findById(rp.getPermission().getPermissionID())
                    .orElseThrow(() -> new RuntimeException("Permission not found"));
            return new PermissionAssignDTO(role.getRoleName(), permission.getPermissionName());
        }).collect(Collectors.toList());
    }

    @Override
    public void createRolePermissions(String roleName, String permissionName) {
            Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
            System.out.println(role.getRoleID());
            Permission permission = permissionRepository.findByPermissionName(permissionName)
                    .orElseGet(() ->{
                        Permission newpermission = new Permission(permissionName);
                        return permissionRepository.save(newpermission);
                    });
            System.out.println(permission.getPermissionID());

            RolePermissionId id = new RolePermissionId(role.getRoleID(),permission.getPermissionID());
            if (rolePermissionRepository.existsById(id)) {
                throw new RuntimeException("Role permission already exists");
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(id);
            rolePermission.setRole(role);
            rolePermission.setPermission(permission);
            rolePermissionRepository.save(rolePermission);
    }

    public void deleteRolePermissions(String roleName,String permissionName) {
        System.out.println(roleName+" "+permissionName);
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
        System.out.println(role.getRoleID());
        Permission permission = permissionRepository.findByPermissionName(permissionName).orElseThrow(() -> new RuntimeException("Permission not found"));
        System.out.println(permission.getPermissionID());
        RolePermissionId id = new RolePermissionId(role.getRoleID(),permission.getPermissionID());
        rolePermissionRepository.deleteById(id);


    }

    @Transactional
    @Override
    public boolean updateRolePermissions(String roleName, String oldPermissionName, String newPermissionName) {
        // 1. Tìm Role dựa trên roleName
        Optional<Role> roleOptional = roleRepository.findByRoleName(roleName);
        if (roleOptional.isEmpty()) {
            return false;
        }
        Role role = roleOptional.get();

        // 2. Tìm Permission cũ dựa trên oldPermissionName (để xác định bản ghi Permission cần update)
        Optional<Permission> oldPermissionOptional = permissionRepository.findByPermissionName(oldPermissionName);
        if (oldPermissionOptional.isEmpty()) {
            return false;
        }
        Permission permissionToUpdate = oldPermissionOptional.get();

        // 3. Tìm Permission mới dựa trên newPermissionName (nếu tên mới khác tên cũ)
        if (!oldPermissionName.equals(newPermissionName)) {
            Optional<Permission> existingNewPermissionOptional = permissionRepository.findByPermissionName(newPermissionName);
            if (existingNewPermissionOptional.isPresent()) {
                // Nếu quyền mới đã tồn tại, bạn có thể chọn:
                // a) Cập nhật tên của quyền cũ thành tên của quyền đã tồn tại (có thể không phải là ý bạn)
                // b) Báo lỗi vì tên mới đã tồn tại (nếu permissionName là duy nhất)
                // c) Tiếp tục cập nhật tên của quyền cũ thành newPermissionName
                permissionToUpdate.setPermissionName(newPermissionName);
                permissionRepository.save(permissionToUpdate);
            } else {
                // Nếu quyền mới chưa tồn tại, cập nhật tên của quyền cũ thành tên mới
                permissionToUpdate.setPermissionName(newPermissionName);
                permissionRepository.save(permissionToUpdate);
            }
            return true;
        }

        // Nếu oldPermissionName và newPermissionName giống nhau, không cần cập nhật
        return true;
    }

//    @Transactional
//    @Override
//    public boolean updateRolePermissions(String roleName, String oldPermissionName, String newPermissionName) {
//        // 1. Tìm Permission cần cập nhật dựa trên oldPermissionName
//        Permission permissionToUpdate = permissionRepository.findByPermissionName(oldPermissionName).orElseThrow(() -> new RuntimeException("Permission not found"));
//
//        // 2. Kiểm tra xem newPermissionName đã tồn tại chưa
//        Optional<Permission> existingNewPermission = permissionRepository.findByPermissionName(newPermissionName);
//
//        Permission permissionToAssign;
//        if (existingNewPermission.isPresent()) {
//            // Nếu newPermissionName đã tồn tại, sử dụng nó
//            permissionToAssign = existingNewPermission.get();
//        } else {
//            // Nếu newPermissionName chưa tồn tại, tạo mới
//            Permission newPermission = new Permission();
//            newPermission.setPermissionName(newPermissionName);
//            permissionToAssign = permissionRepository.save(newPermission);
//        }
//
//        // 3. Cập nhật permissionName của quyền cũ (nếu logic là đổi tên)
//        if (!permissionToUpdate.getPermissionName().equals(newPermissionName)) {
//            permissionToUpdate.setPermissionName(newPermissionName);
//            permissionRepository.save(permissionToUpdate);
//        }
//        return true;
//    }


}
