package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Internal;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolePermissionId implements Serializable {
    @Column(name = "role_id")
    private Integer roleID;

    @Column(name = "permission_id")
    private Integer permissionID;

    // Constructor, getters, setters
    public RolePermissionId() {}

    public RolePermissionId(Integer roleID, Integer permissionID) {
        this.roleID = roleID;
        this.permissionID = permissionID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionId that = (RolePermissionId) o;
        return Objects.equals(roleID, that.roleID) &&
                Objects.equals(permissionID, that.permissionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, permissionID);
    }
}


