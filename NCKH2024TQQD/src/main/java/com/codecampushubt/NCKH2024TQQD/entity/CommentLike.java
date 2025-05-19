package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CommentLikes")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentLikeID")
    private Long commentLikeId;

//    @ManyToOne
//    @JoinColumn(name = "CommentID")
//    private Comment comment;
//
//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getCommentLikeId() {
        return commentLikeId;
    }

    public void setCommentLikeId(Long commentLikeId) {
        this.commentLikeId = commentLikeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public CommentLike() {}
    public CommentLike(Long commentLikeId, LocalDateTime createdAt) {
        this.commentLikeId = commentLikeId;
        this.createdAt = createdAt;
    }

}
