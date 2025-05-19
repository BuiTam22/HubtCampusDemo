package com.codecampushubt.NCKH2024TQQD.dto.LessonDTO;

public class LessonShowDTOA {
    private Long lessonId;
    private String courseModule;
    private String title;
    private String description;
    private String type;
    private String content;
    private Integer duration;
    private String rolename;
    private String userName;

    public LessonShowDTOA() {}

    public LessonShowDTOA(Long lessonId, String courseModule,String title, String description, String type, String content, Integer duration, String rolename, String userName) {
        this.lessonId = lessonId;
        this.courseModule = courseModule;
        this.title = title;
        this.description = description;
        this.type = type;
        this.content = content;
        this.duration = duration;
        this.rolename = rolename;
        this.userName = userName;
    }

    public String getCourseModule() {
        return courseModule;
    }

    public void setCourseModule(String courseModule) {
        this.courseModule = courseModule;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LessonShowDTOA{" +
                "lessonId=" + lessonId +
                ", title='" + title + '\'' +
                ",courseModule='" + courseModule + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", rolename='" + rolename + '\'' +
                ", userName='" + userName + '\'' +

                '}';
    }
}
