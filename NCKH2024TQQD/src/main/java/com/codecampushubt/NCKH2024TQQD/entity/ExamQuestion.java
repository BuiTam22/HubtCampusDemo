package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ExamQuestions")
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;
    @ManyToOne
    @JoinColumn(name = "ExamID", nullable = false)
    private Exam examID;

    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "Points", columnDefinition = "INT DEFAULT 1")
    private int points = 1;

    @Column(name = "OrderIndex")
    private Integer orderIndex;

    @Column(name = "Options", columnDefinition = "NVARCHAR(MAX)")
    private String options;

    @Column(name = "CorrectAnswer", columnDefinition = "NVARCHAR(MAX)")
    private String correctAnswer;

    @Column(name = "Explanation", columnDefinition = "NVARCHAR(MAX)")
    private String explanation;

    @Column(name = "CreatedAt", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Constructor

    public ExamQuestion() {
    }

    public ExamQuestion(Exam examID, String type, String content, int points, Integer orderIndex, String options, String correctAnswer, String explanation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.examID = examID;
        this.type = type;
        this.content = content;
        this.points = points;
        this.orderIndex = orderIndex;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getter & setter

    public Long getQuestionID() {
        return questionID;
    }

    public Exam getExamID() {
        return examID;
    }

    public void setExamID(Exam examID) {
        this.examID = examID;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    @Override
    public String toString() {
        return "ExamQuestion{" +
                "questionID=" + questionID +
                ", examID=" + examID +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", points=" + points +
                ", orderIndex=" + orderIndex +
                ", options='" + options + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

