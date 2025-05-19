package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class EditLessonDTO {
    private Long lessonID;
    private String title;
    private String description;
    private String image;
    private Integer duration;
    private String type;
    private Boolean isContest;
    private LocalDateTime contestStartTime;
    private LocalDateTime contestEndTime;
    private String slug;

    public EditLessonDTO(Long lessonID, String title, String description, String image, Integer duration, String type, Boolean isContest, LocalDateTime contestStartTime, LocalDateTime contestEndTime, String slug) {
        this.lessonID = lessonID;
        this.title = title;
        this.description = description;
        this.image = image;
        this.duration = duration;
        this.type = type;
        this.isContest = isContest;
        this.contestStartTime = contestStartTime;
        this.contestEndTime = contestEndTime;
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLessonID() {
        return lessonID;
    }

    public void setLessonID(Long lessonID) {
        this.lessonID = lessonID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsContest() {
        return isContest;
    }

    public void setIsContest(Boolean isContest) {
        isContest = isContest;
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
