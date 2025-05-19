package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PostTags")
//@IdClass(PostTags.PostTagsId.class)
public class PostTag {
//    ddowij loi 2 khoa chinh
    @Id
    private int id;

//    @Column(name = "PostID")
//    private Long postId;
//
//    @Id
//    @Column(name = "TagID")
//    private Integer tagId;
//
//    @Column(name = "CreatedAt")
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @ManyToOne
//    @JoinColumn(name = "PostID", insertable = false, updatable = false)
//    private PostTag post;
//
//    @ManyToOne
//    @JoinColumn(name = "TagID", insertable = false, updatable = false)
//    private Tag tag;

}