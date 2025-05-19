
package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Table(name = "EventRounds")
public class EventRound {

    // RoundID - Khóa chính, không null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoundID", nullable = false, updatable = false)
    private Long roundID;

    // EventID - bigint, có thể null
    @Column(name = "EventID")
    private Long eventID;

    // Name - nvarchar(255), có thể null
    @Column(name = "Name", length = 255)
    private String name;

    // Duration - int, có thể null
    @Column(name = "Duration")
    private Integer duration;

    // Problems - int, có thể null
    @Column(name = "Problems")
    private Integer problems;

    // Description - nvarchar(MAX), có thể null
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    // StartTime - datetime, có thể null
    @Column(name = "StartTime")
    private LocalDateTime startTime;

    // EndTime - datetime, không null
    @Column(name = "EndTime", nullable = false)
    private LocalDateTime endTime;

    // Constructor mặc định
    public EventRound() {
    }

    // Constructor đầy đủ
    public EventRound(Long eventID, String name, Integer duration, Integer problems, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.eventID = eventID;
        this.name = name;
        this.duration = duration;
        this.problems = problems;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter và Setter
    public Long getRoundID() {
        return roundID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getProblems() {
        return problems;
    }

    public void setProblems(Integer problems) {
        this.problems = problems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    // toString để hiển thị thông tin
    @Override
    public String toString() {
        return "Round{" +
                "roundID=" + roundID +
                ", eventID=" + eventID +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", problems=" + problems +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

