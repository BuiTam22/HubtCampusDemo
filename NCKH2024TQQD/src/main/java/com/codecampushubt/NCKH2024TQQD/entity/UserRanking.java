package com.codecampushubt.NCKH2024TQQD.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserRankings")
public class UserRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RankingID")
    private Long rankingId;

    @Column(name = "UserID", nullable = false)
    private Long userId;

    @Column(name = "Tier", nullable = false, length = 20)
    private String tier;

    @Column(name = "TotalPoints", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalPoints;

    @Column(name = "EventPoints", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer eventPoints;

    @Column(name = "CoursePoints", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coursePoints;

    @Column(name = "ProblemsSolved", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer problemsSolved;

    @Column(name = "Accuracy", nullable = false, precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) DEFAULT 0")
    private BigDecimal accuracy;

    @Column(name = "Wins", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer wins;

    @Column(name = "MonthlyScore", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer monthlyScore;

    @Column(name = "WeeklyScore", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer weeklyScore;

    @Column(name = "LastCalculatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime lastCalculatedAt;

    // Constructor mặc định
    public UserRanking() {
    }

    public UserRanking(String tier, Integer totalPoints, Integer eventPoints, Integer coursePoints, Integer problemsSolved, BigDecimal accuracy, Integer wins, Integer monthlyScore, Integer weeklyScore, LocalDateTime lastCalculatedAt, Long rankingId) {
        this.tier = tier;
        this.totalPoints = totalPoints;
        this.eventPoints = eventPoints;
        this.coursePoints = coursePoints;
        this.problemsSolved = problemsSolved;
        this.accuracy = accuracy;
        this.wins = wins;
        this.monthlyScore = monthlyScore;
        this.weeklyScore = weeklyScore;
        this.lastCalculatedAt = lastCalculatedAt;
        this.rankingId = rankingId;
    }

    public Long getRankingId() {
        return rankingId;
    }

    public void setRankingId(Long rankingId) {
        this.rankingId = rankingId;
    }

    public Long getUserId() {
        return userId;
    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(Integer eventPoints) {
        this.eventPoints = eventPoints;
    }

    public Integer getCoursePoints() {
        return coursePoints;
    }

    public void setCoursePoints(Integer coursePoints) {
        this.coursePoints = coursePoints;
    }

    public Integer getProblemsSolved() {
        return problemsSolved;
    }

    public void setProblemsSolved(Integer problemsSolved) {
        this.problemsSolved = problemsSolved;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getMonthlyScore() {
        return monthlyScore;
    }

    public void setMonthlyScore(Integer monthlyScore) {
        this.monthlyScore = monthlyScore;
    }

    public Integer getWeeklyScore() {
        return weeklyScore;
    }

    public void setWeeklyScore(Integer weeklyScore) {
        this.weeklyScore = weeklyScore;
    }

    public LocalDateTime getLastCalculatedAt() {
        return lastCalculatedAt;
    }

    public void setLastCalculatedAt(LocalDateTime lastCalculatedAt) {
        this.lastCalculatedAt = lastCalculatedAt;
    }

    @Override
    public String toString() {
        return "UserRanking{" +
                "rankingId=" + rankingId +
                ", userId=" + userId +
                ", tier='" + tier + '\'' +
                ", totalPoints=" + totalPoints +
                ", eventPoints=" + eventPoints +
                ", coursePoints=" + coursePoints +
                ", problemsSolved=" + problemsSolved +
                ", accuracy=" + accuracy +
                ", wins=" + wins +
                ", monthlyScore=" + monthlyScore +
                ", weeklyScore=" + weeklyScore +
                ", lastCalculatedAt=" + lastCalculatedAt +
                '}';
    }
}