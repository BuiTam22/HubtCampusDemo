package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PostMedia")
public class PostMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaID")
    private Long mediaId;

//    @ManyToOne
//    @JoinColumn(name = "PostID")
//    private Post post;

    @Column(name = "MediaUrl", length = 255, nullable = false)
    private String mediaUrl;

    @Column(name = "MediaType", length = 20)
    private String mediaType;

    @Column(name = "ThumbnailUrl", length = 255)
    private String thumbnailUrl;

    @Column(name = "Size")
    private Integer size;

    @Column(name = "Width")
    private Integer width;

    @Column(name = "Height")
    private Integer height;

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public PostMedia() {
    }

    public PostMedia(Long mediaId, String mediaUrl, String mediaType, String thumbnailUrl, Integer size, Integer width, Integer height, Integer duration, LocalDateTime createdAt) {
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.thumbnailUrl = thumbnailUrl;
        this.size = size;
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.createdAt = createdAt;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
