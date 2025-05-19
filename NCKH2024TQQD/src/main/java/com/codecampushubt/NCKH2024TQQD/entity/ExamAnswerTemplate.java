package com.codecampushubt.NCKH2024TQQD.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "ExamAnswerTemplates")
public class ExamAnswerTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateID;
    @ManyToOne
    @JoinColumn(name = "ExamID", nullable = false)
    private Exam examID;

    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "Keywords", columnDefinition = "NVARCHAR(MAX)")
    private String keywords;

    @Column(name = "MinimumMatchPercentage", precision = 5, scale = 2)
    private BigDecimal minimumMatchPercentage;
    @ManyToOne
    @JoinColumn(name = "CreatedBy", nullable = false)
    private User createdBy;

    @Column(name = "CreatedAt", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Constructor

    public ExamAnswerTemplate() {
    }

    public ExamAnswerTemplate(Exam examID, String content, String keywords, BigDecimal minimumMatchPercentage, User createdBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.examID = examID;
        this.content = content;
        this.keywords = keywords;
        this.minimumMatchPercentage = minimumMatchPercentage;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//    getter & setter

    public Long getTemplateID() {
        return templateID;
    }

    public Exam getExamID() {
        return examID;
    }

    public void setExamID(Exam examID) {
        this.examID = examID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public BigDecimal getMinimumMatchPercentage() {
        return minimumMatchPercentage;
    }

    public void setMinimumMatchPercentage(BigDecimal minimumMatchPercentage) {
        this.minimumMatchPercentage = minimumMatchPercentage;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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
        return "ExamAnswerTemplate{" +
                "templateID=" + templateID +
                ", examID=" + examID +
                ", content='" + content + '\'' +
                ", keywords='" + keywords + '\'' +
                ", minimumMatchPercentage=" + minimumMatchPercentage +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
