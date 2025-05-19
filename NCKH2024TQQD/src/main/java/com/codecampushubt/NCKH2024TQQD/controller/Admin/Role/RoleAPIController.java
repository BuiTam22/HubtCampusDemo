package com.codecampushubt.NCKH2024TQQD.controller.Admin.Role;

import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionAssignDTO;
import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.UpdatePermissionsDTO;
import com.codecampushubt.NCKH2024TQQD.service.PermissionServices.PermissionService;
import com.codecampushubt.NCKH2024TQQD.service.RolePermissionsSecvice.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/role")
public class RoleAPIController {
    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;
    private final HttpCodeStatusMapper healthHttpCodeStatusMapper;

    public RoleAPIController(PermissionService permissionService, RolePermissionService rolePermissionService, HttpCodeStatusMapper healthHttpCodeStatusMapper) {
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
        this.healthHttpCodeStatusMapper = healthHttpCodeStatusMapper;
    }


    @GetMapping("/show")
    public ResponseEntity<List<PermissionAssignDTO>> getPermissionsByRole() {
        List<PermissionAssignDTO> permissions = rolePermissionService.getAllRolePermissions();
        return ResponseEntity.ok(permissions);
    }

    @PostMapping("/permissionsAdd")
    public ResponseEntity<?> createRolePermission(@RequestBody PermissionAssignDTO dto) {
        try {
//            System.out.println("controller"+dto);

            rolePermissionService.createRolePermissions(dto.getRoleName(), dto.getPermissionName());
            return ResponseEntity.ok("Role Permission created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }


    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRolePermissions(@RequestParam String roleName, @RequestParam String permissionName) {
        System.out.println("controller: " + roleName + " " + permissionName);

        try {
            rolePermissionService.deleteRolePermissions(roleName, permissionName);
            return ResponseEntity.status(HttpStatus.OK).body("Xóa thành công!");
        } catch (RuntimeException e) {
            // Xử lý lỗi khi không tìm thấy role hoặc permission
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy role hoặc permission");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRolePermission(@RequestBody UpdatePermissionsDTO dto) {

        rolePermissionService.updateRolePermissions(dto.getRoleName(), dto.getOldPermissionName(), dto.getNewPermissionName());
        // Record hoặc Class DTO để nhận dữ liệu request
        return ResponseEntity.ok(Map.of("message", "Permissions updated successfully"));
    }



}
