package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "RankingStats")
public class RankingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StatID", nullable = false)
    private Long statID;

    @Column(name = "UserID")
    private Long userID;

    @Column(name = "PeriodType", length = 20)
    private String periodType;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "TotalPoints")
    private Integer totalPoints;

    @Column(name = "EventsParticipated")
    private Integer eventsParticipated;

    @Column(name = "CoursesCompleted")
    private Integer coursesCompleted;

    @Column(name = "AverageAccuracy", precision = 5, scale = 2, nullable = false)
    private BigDecimal averageAccuracy;

    // Constructor mặc định
    public RankingStats() {
    }

    // Getters và Setters

    public Long getStatID() {
        return statID;
    }

    public void setStatID(Long statID) {
        this.statID = statID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getEventsParticipated() {
        return eventsParticipated;
    }

    public void setEventsParticipated(Integer eventsParticipated) {
        this.eventsParticipated = eventsParticipated;
    }

    public Integer getCoursesCompleted() {
        return coursesCompleted;
    }

    public void setCoursesCompleted(Integer coursesCompleted) {
        this.coursesCompleted = coursesCompleted;
    }

    public BigDecimal getAverageAccuracy() {
        return averageAccuracy;
    }

    public void setAverageAccuracy(BigDecimal averageAccuracy) {
        this.averageAccuracy = averageAccuracy;
    }
}
