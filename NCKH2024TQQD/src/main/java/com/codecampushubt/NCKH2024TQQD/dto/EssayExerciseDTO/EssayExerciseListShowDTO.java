package com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO;

public class EssayExerciseListShowDTO {
    private Long exerciseID;
    private String lessonTitle;
    private String title;
    private String description;
    private String subjectName;
    private String difficulty;
    private Integer points;
    private String slug;

    public EssayExerciseListShowDTO(Long exerciseID, String lessonTitle, String title, String description, String subjectName, String difficulty, Integer points, String slug) {
        this.exerciseID = exerciseID;
        this.lessonTitle = lessonTitle;
        this.title = title;
        this.description = description;
        this.subjectName = subjectName;
        this.difficulty = difficulty;
        this.points = points;
        this.slug = slug;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
