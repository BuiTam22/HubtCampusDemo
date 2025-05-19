package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MessageStatus")
public class MessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StatusID")
    private Long statusId;

//    @ManyToOne
//    @JoinColumn(name = "MessageID")
//    private Message message;
//
//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "Status", length = 20)
    private String status = "sent";

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public MessageStatus() {
    }

    public MessageStatus(Long statusId, String status, LocalDateTime updatedAt) {
        this.statusId = statusId;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}