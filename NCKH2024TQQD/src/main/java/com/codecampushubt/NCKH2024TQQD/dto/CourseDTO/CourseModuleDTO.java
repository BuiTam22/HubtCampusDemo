package com.codecampushubt.NCKH2024TQQD.dto.CourseDTO;

public class CourseModuleDTO {
    private Long id;
    private String title;
    private String description;
    private Integer orderIndex;
    private Integer duration;
    private Boolean isPublished;

    public CourseModuleDTO(Long id, String title, String description, Integer orderIndex, Integer duration, Boolean isPublished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.orderIndex = orderIndex;
        this.duration = duration;
        this.isPublished = isPublished;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getPublished() {
        return isPublished;
    }
}
