package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

import java.time.LocalDateTime;

public class CreateLessonClientDTO {
    private String title;
    private String type;
    private Integer duration;
    private Boolean isContest;
    private LocalDateTime contestStartTime;
    private LocalDateTime contestEndTime;

    public CreateLessonClientDTO(String title, String type, Integer duration, Boolean isContest, LocalDateTime contestStartTime, LocalDateTime contestEndTime) {
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.isContest = isContest;
        this.contestStartTime = contestStartTime;
        this.contestEndTime = contestEndTime;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getIsContest() {
        return isContest;
    }

    public LocalDateTime getContestStartTime() {
        return contestStartTime;
    }

    public LocalDateTime getContestEndTime() {
        return contestEndTime;
    }
}
