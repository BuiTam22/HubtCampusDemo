package com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO;

public class CodingExerciseDTO {
    private Long exerciseID;
    private String lessonTitle;
    private String title;
    private String description;
    private String programLanguage;
    private String difficulty;
    private Integer points;
    private String slug;

    public CodingExerciseDTO(Long exerciseID, String lessonTitle, String title, String description, String programLanguage, String difficulty, Integer points, String slug) {
        this.exerciseID = exerciseID;
        this.lessonTitle = lessonTitle;
        this.title = title;
        this.description = description;
        this.programLanguage = programLanguage;
        this.difficulty = difficulty;
        this.points = points;
        this.slug = slug;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProgramLanguage() {
        return programLanguage;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public String getSlug() {
        return slug;
    }
}