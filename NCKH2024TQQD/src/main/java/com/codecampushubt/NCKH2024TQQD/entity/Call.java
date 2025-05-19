package com.codecampushubt.NCKH2024TQQD.entity;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CallID")
    private Long callId;

//    @ManyToOne
//    @JoinColumn(name = "ConversationID")
//    private Conversations conversation;
//
//    @ManyToOne
//    @JoinColumn(name = "InitiatorID")
//    private User initiator;

    @Column(name = "Type")
    private String type;

    @Column(name = "StartTime")
    private LocalDateTime startTime = LocalDateTime.now();

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "Status")
    private String status = "initiated";

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "Quality")
    private String quality;

    @Column(name = "RecordingUrl", length = 255)
    private String recordingUrl;

    // Constructors, getters, setters

    public Call(Long callId, String type, LocalDateTime startTime, LocalDateTime endTime, String status, Integer duration, String quality, String recordingUrl) {
        this.callId = callId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.duration = duration;
        this.quality = quality;
        this.recordingUrl = recordingUrl;
    }
    public Call() {}

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;


    }
}
