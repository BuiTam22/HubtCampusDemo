package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer permissionID;

    @Column(name = "permissionName", nullable = false, unique = true, length = 50)
    private String permissionName;

    @OneToMany(mappedBy = "permission",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermission> rolePermissions;

    public Permission() {
    }

    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionID=" + permissionID +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }

    //Khi sử dụng Set<RolePermission> trong Permission và Role, Hibernate có thể bị lỗi khi so sánh object trong bộ nhớ đệm
    //Việc override equals() và hashCode() giúp đảm bảo các entity được so sánh đúng dựa trên ID duy nhất, tránh trùng lặp dữ liệu trong Set<>.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(permissionID, that.permissionID) ||
                Objects.equals(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionID, permissionName);
    }


}
