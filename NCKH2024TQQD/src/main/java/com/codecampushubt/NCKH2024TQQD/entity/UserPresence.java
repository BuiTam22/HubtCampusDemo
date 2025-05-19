package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserPresence")
public class UserPresence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long presenceId;

//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "Status")
    private String status = "offline";

    @Column(name = "LastActiveAt")
    private LocalDateTime lastActiveAt = LocalDateTime.now();

    @Column(name = "CurrentDeviceID")
    private String currentDeviceId;

    @Column(name = "LastLocation", columnDefinition = "NVARCHAR(MAX)")
    private String lastLocation;

    public UserPresence(){

    };
    public UserPresence(Long presenceId, String status, LocalDateTime lastActiveAt, String currentDeviceId, String lastLocation) {
        this.presenceId = presenceId;
        this.status = status;
        this.lastActiveAt = lastActiveAt;
        this.currentDeviceId = currentDeviceId;
        this.lastLocation = lastLocation;
    }

    public Long getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(Long presenceId) {
        this.presenceId = presenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    public String getCurrentDeviceId() {
        return currentDeviceId;
    }

    public void setCurrentDeviceId(String currentDeviceId) {
        this.currentDeviceId = currentDeviceId;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }
}
