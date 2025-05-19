package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "RolePermission")
public class RolePermission {
    @EmbeddedId
    private RolePermissionId id;


    @ManyToOne
    @MapsId("roleID")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("permissionID")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;


    public RolePermissionId getId() {
        return id;
    }

    public void setId(RolePermissionId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermission that = (RolePermission) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}
