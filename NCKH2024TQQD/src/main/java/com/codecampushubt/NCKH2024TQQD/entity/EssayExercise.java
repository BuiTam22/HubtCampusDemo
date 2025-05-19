package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EssayExercises")
public class EssayExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExerciseID", nullable = false, updatable = false)
    private Long exerciseID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSELESSONS
    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private CourseLesson lesson;

    // TIÊU ĐỀ BÀI TẬP
    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    // MÔ TẢ CHI TIẾT BÀI TẬP
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    // TÊN MÔN HỌC
    @Column(name = "SubjectName", columnDefinition = "NVARCHAR(255)")
    private String subjectName;

    // Đáp án
    @Column(name = "ExpectedAnswer", columnDefinition = "NVARCHAR(MAX)")
    private String expectedAnswer;

    // THỜI GIAN TỐI ĐA
    @Column(name = "TimeLimit", nullable = true)
    private Integer timeLimit;

    // ĐỘ KHÓ
    @Column(name = "Difficulty", nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'medium'")
    private String difficulty;

    // ĐIỂM CHO BÀI TẬP
    @Column(name = "Points", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer points;

    // THỜI ĐIỂM TẠO
    @Column(name = "CreatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createdAt;

    // THỜI ĐIỂM CẬP NHẬT
    @Column(name = "UpdatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime updatedAt;

    // SLUG
    @Column(name = "Slug", nullable = true)
    private String slug;


    public EssayExercise() {
    }

    public EssayExercise(CourseLesson lesson, String title, String description, String subjectName, String expectedAnswer, Integer timeLimit, String difficulty, Integer points, LocalDateTime createdAt, LocalDateTime updatedAt, String slug) {
        this.lesson = lesson;
        this.title = title;
        this.description = description;
        this.subjectName = subjectName;
        this.expectedAnswer = expectedAnswer;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.points = points;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.slug = slug;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public CourseLesson getLesson() {
        return lesson;
    }

    public void setLesson(CourseLesson lesson) {
        this.lesson = lesson;
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

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
