package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "Conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConversationID")
    private Long conversationId;

    @Column(name = "Type")
    private String type = "private";

    @Column(name = "Title", length = 255)
    private String title;

//    @ManyToOne
//    @JoinColumn(name = "CreatedBy")
//    private User createdBy;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "LastMessageAt")
    private LocalDateTime lastMessageAt;

    @Column(name = "IsActive")
    private boolean isActive = true;

    public Conversation(Long conversationId, String type, String title, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastMessageAt, boolean isActive) {
        this.conversationId = conversationId;
        this.type = type;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastMessageAt = lastMessageAt;
        this.isActive = isActive;
    }
    public Conversation() {}

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastMessageAt() {
        return lastMessageAt;
    }

    public void setLastMessageAt(LocalDateTime lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    // Constructors, getters, setters
}