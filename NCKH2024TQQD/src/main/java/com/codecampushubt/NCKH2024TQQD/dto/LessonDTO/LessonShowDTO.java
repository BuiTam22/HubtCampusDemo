package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

public class LessonShowDTO {
    private Long lessonID;
    private Long moduleID;
    private String title;
    private String description;
    private String type;
    private String content;
    private String image;
    private Integer duration;
    private Integer orderIndex;
    private Boolean isPreview;
    private Boolean isPublished;
    private String slug;

    public LessonShowDTO(Long lessonID, Long moduleID, String title, String description, String type, String content, String image, Integer duration, Integer orderIndex, Boolean isPreview, Boolean isPublished, String slug) {
        this.lessonID = lessonID;
        this.moduleID = moduleID;
        this.title = title;
        this.description = description;
        this.type = type;
        this.content = content;
        this.image = image;
        this.duration = duration;
        this.orderIndex = orderIndex;
        this.isPreview = isPreview;
        this.isPublished = isPublished;
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public Long getLessonID() {
        return lessonID;
    }

    public Long getModuleID() {
        return moduleID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public Boolean getPreview() {
        return isPreview;
    }

    public Boolean getPublished() {
        return isPublished;
    }
}
