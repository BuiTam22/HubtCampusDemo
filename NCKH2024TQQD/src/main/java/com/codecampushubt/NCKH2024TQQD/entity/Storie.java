package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Stories")
public class Storie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyID;

//    @ManyToOne
//    @JoinColumn(name = "userID", nullable = false)
//    private User user;

    private String mediaUrl;
    private String mediaType;
    private Integer duration = 15;
    private Integer viewCount = 0;
    private String backgroundColor;
    private String textContent;
    private String fontStyle;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt;
    private Boolean isDeleted = false;

    protected Storie() {
    }

    public Long getStoryID() {
        return storyID;
    }

    public void setStoryID(Long storyID) {
        this.storyID = storyID;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Storie(Boolean isDeleted, LocalDateTime expiresAt, LocalDateTime createdAt, String fontStyle, String textContent, String backgroundColor, Integer viewCount, Integer duration, String mediaType, String mediaUrl) {
        this.isDeleted = isDeleted;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.fontStyle = fontStyle;
        this.textContent = textContent;
        this.backgroundColor = backgroundColor;
        this.viewCount = viewCount;
        this.duration = duration;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
    }
}

