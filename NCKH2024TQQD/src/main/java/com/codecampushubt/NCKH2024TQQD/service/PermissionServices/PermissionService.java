package com.codecampushubt.NCKH2024TQQD.service.PermissionServices;

import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.PermissionAssignDTO;
import com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO.UpdatePermissionsDTO;


import java.util.List;

public interface PermissionService {
    List<String> getPermissionNameDTO(String userName);
//    void assignPermission(PermissionAssignDTO permissionAssignDTO);
//
//
//    void updatePermissions(UpdatePermissionsDTO updatePermissionsDTO);
//void assignPermission(PermissionAssignDTO dto);

//    List<PermissionAssignDTO> getAllRolePermissions();
}
