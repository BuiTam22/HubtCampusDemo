package com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO;

import com.codecampushubt.NCKH2024TQQD.entity.Message;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UpdatePermissionsDTO {
    private String roleName;
    private String oldPermissionName;
    private String newPermissionName;
    public UpdatePermissionsDTO() {}

    public UpdatePermissionsDTO(String newPermissionName, String oldPermissionName, String roleName) {
        this.newPermissionName = newPermissionName;
        this.oldPermissionName = oldPermissionName;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOldPermissionName() {
        return oldPermissionName;
    }

    public void setOldPermissionName(String oldPermissionName) {
        this.oldPermissionName = oldPermissionName;
    }

    public String getNewPermissionName() {
        return newPermissionName;
    }

    public void setNewPermissionName(String newPermissionName) {
        this.newPermissionName = newPermissionName;
    }

    @Override
    public String toString() {
        return "UpdatePermissionsDTO{" +
                "roleName='" + roleName + '\'' +
                ", oldPermissionName='" + oldPermissionName + '\'' +
                ", newPermissionName='" + newPermissionName + '\'' +
                '}';
    }
}
