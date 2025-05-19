package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ConversationParticipants")
public class ConversationParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParticipantID")
    private Long participantId;

//    @ManyToOne
//    @JoinColumn(name = "ConversationID")
//    private Conversation conversation;
//
//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "JoinedAt")
    private LocalDateTime joinedAt = LocalDateTime.now();

    @Column(name = "LeftAt")
    private LocalDateTime leftAt;

    @Column(name = "Role", length = 20)
    private String role = "member";

    @Column(name = "LastReadMessageID")
    private Long lastReadMessageId;

    @Column(name = "IsAdmin")
    private boolean isAdmin = false;

    @Column(name = "IsMuted")
    private boolean isMuted = false;

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getLastReadMessageId() {
        return lastReadMessageId;
    }

    public void setLastReadMessageId(Long lastReadMessageId) {
        this.lastReadMessageId = lastReadMessageId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }


        public ConversationParticipant() {
    }

    public ConversationParticipant(Long participantId, LocalDateTime joinedAt, LocalDateTime leftAt, String role, Long lastReadMessageId, boolean isAdmin, boolean isMuted) {
        this.participantId = participantId;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.role = role;
        this.lastReadMessageId = lastReadMessageId;
        this.isAdmin = isAdmin;
        this.isMuted = isMuted;
    }
}