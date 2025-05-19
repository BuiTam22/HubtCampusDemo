package com.codecampushubt.NCKH2024TQQD.service.RolePermissionsSecvice;

import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionAssignDTO;

import java.util.List;

public interface RolePermissionService {
    List<PermissionAssignDTO> getAllRolePermissions();
    void createRolePermissions(String roleName, String permissionName);
    void deleteRolePermissions(String roleName , String permissionName);
    boolean updateRolePermissions(String roleName, String oldPermissionName, String newPermissionName);
}
