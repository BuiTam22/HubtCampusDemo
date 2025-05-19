package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RankingHistory")
public class RankingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoryID", nullable = false)
    private Long historyID;

    @Column(name = "UserID")
    private Long userID;

    @Column(name = "Type", length = 20)
    private String type;

    @Column(name = "RelatedID")
    private Long relatedID;

    @Column(name = "PointsEarned")
    private Integer pointsEarned;

    @Column(name = "Reason", length = 255)
    private String reason;

    @Column(name = "CreatedAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructor mặc định
    public RankingHistory() {
    }

    // Getters và Setters

    public Long getHistoryID() {
        return historyID;
    }

    public void setHistoryID(Long historyID) {
        this.historyID = historyID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRelatedID() {
        return relatedID;
    }

    public void setRelatedID(Long relatedID) {
        this.relatedID = relatedID;
    }

    public Integer getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
