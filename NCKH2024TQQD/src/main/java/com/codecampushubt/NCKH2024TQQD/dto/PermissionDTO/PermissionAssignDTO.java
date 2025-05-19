package com.codecampushubt.NCKH2024TQQD.dto.PermissionDTO;

import jakarta.validation.constraints.NotBlank;

public class PermissionAssignDTO {
    private String roleName;
    private String permissionName;

    public PermissionAssignDTO() {}

    public PermissionAssignDTO(String roleName, String permissionName) {
        this.roleName = roleName;
        this.permissionName = permissionName;
    }

    public @NotBlank(message = "RoleName Không Được Để Trống ") String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank(message = "RoleName Không Được Để Trống ") String roleName) {
        this.roleName = roleName;
    }

    public @NotBlank(message = "Permission Không Được Để Trống") String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(@NotBlank(message = "Permission Không Được Để Trống") String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "PermissionAssignDTO{" +
                "roleName='" + roleName + '\'' +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
