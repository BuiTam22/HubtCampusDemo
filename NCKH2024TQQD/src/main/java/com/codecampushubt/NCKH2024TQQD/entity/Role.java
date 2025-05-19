package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role {

    @Id  // Đảm bảo sử dụng javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer roleID;

    @Column(name = "roleName", nullable = false, unique = true, length = 50)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "role",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermission> rolePermissions;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                '}';
    }


    //Khi sử dụng Set<RolePermission> trong Permission và Role, Hibernate có thể bị lỗi khi so sánh object trong bộ nhớ đệm
    //Việc override equals() và hashCode() giúp đảm bảo các entity được so sánh đúng dựa trên ID duy nhất, tránh trùng lặp dữ liệu trong Set<>.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return Objects.equals(roleID, that.roleID) ||
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, roleName);
    }

}
