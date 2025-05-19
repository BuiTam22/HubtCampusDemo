package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserRole")
public class UserRole {

    // Khai báo khóa chính tổng hợp có tên UserRoleId
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    //Ánh xạ đến trường trong khóa chính tổng hợp
    @MapsId("userID")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    //Ánh xạ đến trường trong khóa chính tổng hợp
    @MapsId("roleID")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "assignedAt", nullable = false, updatable = false)
    private LocalDateTime assignedAt = LocalDateTime.now();

    // Getters and Setters
    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

}
