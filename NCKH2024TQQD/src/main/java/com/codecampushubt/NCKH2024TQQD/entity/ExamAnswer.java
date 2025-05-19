package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ExamAnswers")
public class ExamAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerID", nullable = false, unique = true)
    private Long answerID;
    @ManyToOne
    @JoinColumn(name = "ParticipantID", nullable = false)
    private ExamParticipant participantID;
    @ManyToOne
    @JoinColumn(name = "QuestionID", nullable = false)
    private ExamQuestion questionID;

    @Column(name = "Answer", columnDefinition = "NVARCHAR(MAX)")
    private String answer;

    @Column(name = "IsCorrect")
    private Boolean isCorrect;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "ReviewerComments", columnDefinition = "NVARCHAR(MAX)")
    private String reviewerComments;

    @Column(name = "SubmittedAt", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp submittedAt;

    // constructor

    public ExamAnswer() {
    }

    public ExamAnswer(ExamParticipant participantID, ExamQuestion questionID, String answer, Boolean isCorrect, Integer score, String reviewerComments, Timestamp submittedAt) {
        this.participantID = participantID;
        this.questionID = questionID;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.score = score;
        this.reviewerComments = reviewerComments;
        this.submittedAt = submittedAt;
    }

    // getter setter

    public Long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Long answerID) {
        this.answerID = answerID;
    }

    public ExamParticipant getParticipantID() {
        return participantID;
    }

    public void setParticipantID(ExamParticipant participantID) {
        this.participantID = participantID;
    }

    public ExamQuestion getQuestionID() {
        return questionID;
    }

    public void setQuestionID(ExamQuestion questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
}
