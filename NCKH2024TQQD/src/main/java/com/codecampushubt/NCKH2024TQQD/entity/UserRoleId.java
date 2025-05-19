package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

// Khóa chính tổng hợp
@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "user_id")  // Đúng với tên cột trong bảng UserRole
    private Long userID;

    @Column(name = "role_id")  // Đúng với tên cột trong bảng UserRole
    private Integer roleID;

    public UserRoleId() {}

    public UserRoleId(Long userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    // Getters and Setters
    public Long getUserId() {
        return userID;
    }

    public void setUserId(Long userID) {
        this.userID = userID;
    }

    public Integer getRoleId() {
        return roleID;
    }

    public void setRoleId(Integer roleID) {
        this.roleID = roleID;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userID, that.userID) && Objects.equals(roleID, that.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, roleID);
    }
}
