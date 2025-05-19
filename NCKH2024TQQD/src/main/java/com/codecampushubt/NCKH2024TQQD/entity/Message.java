package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageID")
    private Long messageId;

//    @ManyToOne
//    @JoinColumn(name = "ConversationID")
//    private Conversation conversation;
//
//    @ManyToOne
//    @JoinColumn(name = "SenderID")
//    private User sender;

    @Column(name = "Type", length = 20)
    private String type = "text";

    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "MediaUrl", length = 255)
    private String mediaUrl;

    @Column(name = "MediaType", length = 20)
    private String mediaType;

//    @ManyToOne
//    @JoinColumn(name = "ReplyToMessageID")
//    private Message replyToMessage;

    @Column(name = "IsEdited")
    private boolean isEdited = false;

    @Column(name = "IsDeleted")
    private boolean isDeleted = false;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalDateTime deletedAt;

    public Message() {
    }

    public Message(Long messageId, String type, String content, String mediaUrl, String mediaType, Message replyToMessage, boolean isEdited, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.messageId = messageId;
        this.type = type;
        this.content = content;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
//        this.replyToMessage = replyToMessage;
        this.isEdited = isEdited;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
