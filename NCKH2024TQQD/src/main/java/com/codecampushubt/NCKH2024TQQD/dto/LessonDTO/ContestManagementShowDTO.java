package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

import java.time.LocalDateTime;

public class ContestManagementShowDTO {
    private String title;
    private String slug;
    private String userNameOwner;
    private LocalDateTime contestStartTime;
    private LocalDateTime contestEndTime;

    public ContestManagementShowDTO(String title, String slug, String userNameOwner, LocalDateTime contestStartTime, LocalDateTime contestEndTime) {
        this.title = title;
        this.slug = slug;
        this.userNameOwner = userNameOwner;
        this.contestStartTime = contestStartTime;
        this.contestEndTime = contestEndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUserNameOwner() {
        return userNameOwner;
    }

    public void setUserNameOwner(String userNameOwner) {
        this.userNameOwner = userNameOwner;
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
