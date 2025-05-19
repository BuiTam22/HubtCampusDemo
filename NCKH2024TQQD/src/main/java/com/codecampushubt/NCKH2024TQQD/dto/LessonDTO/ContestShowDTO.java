package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

import java.time.LocalDateTime;

public class ContestShowDTO {
    private Long lessonID;
    private String title;
    private String description;
    private String type;
    private Integer duration;
    private String image;
    private Boolean isPreview;
    private String slug;
    private LocalDateTime contestStartTime;
    private LocalDateTime contestEndTime;


    public ContestShowDTO() {
    }

    public ContestShowDTO(Long lessonID, String title, String description, String type, Integer duration, String image, Boolean isPreview, String slug, LocalDateTime contestStartTime, LocalDateTime contestEndTime) {
        this.lessonID = lessonID;
        this.title = title;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.image = image;
        this.isPreview = isPreview;
        this.slug = slug;
        this.contestStartTime = contestStartTime;
        this.contestEndTime = contestEndTime;
    }

    public Long getLessonID() {
        return lessonID;
    }

    public void setLessonID(Long lessonID) {
        this.lessonID = lessonID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(Boolean isPreview) {
        isPreview = isPreview;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public LocalDateTime getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(LocalDateTime contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public LocalDateTime getContestEndTime() {
        return contestEndTime;
    }

    public void setContestEndTime(LocalDateTime contestEndTime) {
        this.contestEndTime = contestEndTime;
    }
}
