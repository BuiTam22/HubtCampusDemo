package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "ContestExerciseAttempts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ExerciseID", "UserID", "ExerciseType"})
)
public class ContestExerciseAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttemptID")
    private Long attemptID;

    @Column(name = "ExerciseID", nullable = false)
    private Long exerciseID;

    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private CourseLesson lesson;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "SubmittedAt", nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "Score")
    private Double score;

    @Column(name = "ExerciseType")
    private String exerciseType;

    @Column(name = "AttemptNumber")
    private Integer attemptNumber;



    public ContestExerciseAttempt() {
    }

    public ContestExerciseAttempt(Long exerciseID, CourseLesson lesson, User user, LocalDateTime submittedAt, Double score, String exerciseType, Integer attemptNumber) {
        this.exerciseID = exerciseID;
        this.lesson = lesson;
        this.user = user;
        this.submittedAt = submittedAt;
        this.score = score;
        this.exerciseType = exerciseType;
        this.attemptNumber = attemptNumber;
    }


    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public Long getAttemptID() {
        return attemptID;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public CourseLesson getLesson() {
        return lesson;
    }

    public void setLesson(CourseLesson lesson) {
        this.lesson = lesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
