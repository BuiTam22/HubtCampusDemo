package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

import com.codecampushubt.NCKH2024TQQD.entity.CourseLesson;

public class LessonResponseDTO {
    private String title;
    private Integer duration;
    private String type;
    private Boolean isContest;

    public LessonResponseDTO(CourseLesson lesson) {;
        this.title = lesson.getTitle();
        this.duration = lesson.getDuration();
        this.type = lesson.getType();
        this.isContest = lesson.getIsContest();
    }


    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public Boolean getContest() {
        return isContest;
    }
}