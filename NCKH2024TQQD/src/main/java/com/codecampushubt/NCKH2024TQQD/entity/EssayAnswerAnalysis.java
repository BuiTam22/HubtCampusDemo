package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "EssayAnswerAnalysis")
public class EssayAnswerAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnalysisID", nullable = false, unique = true)
    private Long analysisID;
    @ManyToOne
    @JoinColumn(name = "AnswerID", nullable = false)
    private ExamAnswer answerID;

    @Column(name = "MatchPercentage", precision = 5, scale = 2)
    private BigDecimal matchPercentage;

    @Column(name = "KeywordsMatched")
    private Integer keywordsMatched;

    @Column(name = "TotalKeywords")
    private Integer totalKeywords;

    @Column(name = "ContentSimilarity", precision = 5, scale = 2)
    private BigDecimal contentSimilarity;

    @Column(name = "GrammarScore", precision = 5, scale = 2)
    private BigDecimal grammarScore;

    @Column(name = "AnalyzedAt", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp analyzedAt;

    @Column(name = "AutoGradedScore")
    private Integer autoGradedScore;

    @Column(name = "FinalScore")
    private Integer finalScore;

    @Column(name = "ReviewerComments", columnDefinition = "NVARCHAR(MAX)")
    private String reviewerComments;

    // constructor
    public EssayAnswerAnalysis() {
    }

    public EssayAnswerAnalysis(ExamAnswer answerID, BigDecimal matchPercentage, Integer keywordsMatched, Integer totalKeywords, BigDecimal contentSimilarity, BigDecimal grammarScore, Timestamp analyzedAt, Integer autoGradedScore, Integer finalScore, String reviewerComments) {
        this.answerID = answerID;
        this.matchPercentage = matchPercentage;
        this.keywordsMatched = keywordsMatched;
        this.totalKeywords = totalKeywords;
        this.contentSimilarity = contentSimilarity;
        this.grammarScore = grammarScore;
        this.analyzedAt = analyzedAt;
        this.autoGradedScore = autoGradedScore;
        this.finalScore = finalScore;
        this.reviewerComments = reviewerComments;
    }

    // getter setter

    public Long getAnalysisID() {
        return analysisID;
    }

    public ExamAnswer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(ExamAnswer answerID) {
        this.answerID = answerID;
    }

    public BigDecimal getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(BigDecimal matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public Integer getKeywordsMatched() {
        return keywordsMatched;
    }

    public void setKeywordsMatched(Integer keywordsMatched) {
        this.keywordsMatched = keywordsMatched;
    }

    public Integer getTotalKeywords() {
        return totalKeywords;
    }

    public void setTotalKeywords(Integer totalKeywords) {
        this.totalKeywords = totalKeywords;
    }

    public BigDecimal getContentSimilarity() {
        return contentSimilarity;
    }

    public void setContentSimilarity(BigDecimal contentSimilarity) {
        this.contentSimilarity = contentSimilarity;
    }

    public BigDecimal getGrammarScore() {
        return grammarScore;
    }

    public void setGrammarScore(BigDecimal grammarScore) {
        this.grammarScore = grammarScore;
    }

    public Timestamp getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(Timestamp analyzedAt) {
        this.analyzedAt = analyzedAt;
    }

    public Integer getAutoGradedScore() {
        return autoGradedScore;
    }

    public void setAutoGradedScore(Integer autoGradedScore) {
        this.autoGradedScore = autoGradedScore;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    @Override
    public String toString() {
        return "EssayAnswerAnalysis{" +
                "analysisID=" + analysisID +
                ", answerID=" + answerID +
                ", matchPercentage=" + matchPercentage +
                ", keywordsMatched=" + keywordsMatched +
                ", totalKeywords=" + totalKeywords +
                ", contentSimilarity=" + contentSimilarity +
                ", grammarScore=" + grammarScore +
                ", analyzedAt=" + analyzedAt +
                ", autoGradedScore=" + autoGradedScore +
                ", finalScore=" + finalScore +
                ", reviewerComments='" + reviewerComments + '\'' +
                '}';
    }
}
