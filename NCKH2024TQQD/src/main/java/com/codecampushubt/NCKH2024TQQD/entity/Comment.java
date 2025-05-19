package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private Long commentId;

//    @ManyToOne
//    @JoinColumn(name = "PostID")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "ParentCommentID")
//    private Comment parentComment;

    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "LikesCount")
    private Integer likesCount = 0;

    @Column(name = "RepliesCount")
    private Integer repliesCount = 0;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "DeletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "IsEdited")
    private boolean isEdited = false;

    @Column(name = "IsDeleted")
    private boolean isDeleted = false;
    public Comment() {}
    public Comment(Long commentId, String content, Integer likesCount, Integer repliesCount, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, boolean isEdited, boolean isDeleted) {
        this.commentId = commentId;
        this.content = content;
        this.likesCount = likesCount;
        this.repliesCount = repliesCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isEdited = isEdited;
        this.isDeleted = isDeleted;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(Integer repliesCount) {
        this.repliesCount = repliesCount;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    //
}
