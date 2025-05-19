package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PostLikes")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LikeID")
    private Long likeId;

//    @ManyToOne
//    @JoinColumn(name = "PostID")
//    private Post post;

//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public PostLike(Long likeId, LocalDateTime createdAt) {
        this.likeId = likeId;
        this.createdAt = createdAt;
    }
    public PostLike() {}

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
