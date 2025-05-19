package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EventSchedule")
public class EventSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleID", nullable = false, updatable = false)
    private Long scheduleID;

    @Column(name = "EventID")
    private Long eventID;

    @Column(name = "ActivityName", length = 255)
    private String activityName;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Location", length = 255)
    private String location;

    @Column(name = "Type", length = 50, nullable = false)
    private String type;

    // Constructor mặc định
    public EventSchedule() {
    }

    // Getters và Setters

    public Long getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Long scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString method để in thông tin
    @Override
    public String toString() {
        return "EventSchedule{" +
                "scheduleID=" + scheduleID +
                ", eventID=" + eventID +
                ", activityName='" + activityName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
