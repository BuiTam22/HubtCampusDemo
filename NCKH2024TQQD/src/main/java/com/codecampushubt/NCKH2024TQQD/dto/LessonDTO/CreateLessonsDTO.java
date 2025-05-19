package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;
import com.codecampushubt.NCKH2024TQQD._enum.Admin.Lesson.type;

public class CreateLessonsDTO {
    private String courseName;
    private String title;
    private String description;
    private String type;
    private String content;
    private Integer duration;
    private String image;
    private Integer orderIndex;
    public CreateLessonsDTO(){}

    public CreateLessonsDTO(String courseName, String title, String description, String type, String content, Integer duration, String image, Integer orderIndex) {
        this.courseName = courseName;
        this.title = title;
        this.description = description;
        this.type = type;
        this.content = content;
        this.duration = duration;
        this.image = image;
        this.orderIndex = orderIndex;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "CreateLessonsDTO{" +
                "courseName='" + courseName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", image='" + image + '\'' +
                ", orderIndex=" + orderIndex +
                '}';
    }
}