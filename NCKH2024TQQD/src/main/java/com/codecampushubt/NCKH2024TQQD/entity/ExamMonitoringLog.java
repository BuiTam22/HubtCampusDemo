package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ExamMonitoringLogs")
public class ExamMonitoringLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID", nullable = false, unique = true)
    private Long logID;

    @ManyToOne
    @JoinColumn(name = "ParticipantID", nullable = false)
    private ExamParticipant participantID;

    @Column(name = "EventType", nullable = false, length = 50)
    private String eventType;

    @Column(name = "EventData", columnDefinition = "NVARCHAR(MAX)")
    private String eventData;

    @Column(name = "Timestamp", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    // constructor

    public ExamMonitoringLog() {
    }

    public ExamMonitoringLog(ExamParticipant participantID, String eventType, String eventData, Timestamp timestamp) {
        this.participantID = participantID;
        this.eventType = eventType;
        this.eventData = eventData;
        this.timestamp = timestamp;
    }

    // getter setter

    public Long getLogID() {
        return logID;
    }

    public ExamParticipant getParticipantID() {
        return participantID;
    }

    public void setParticipantID(ExamParticipant participantID) {
        this.participantID = participantID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ExamMonitoringLog{" +
                "logID=" + logID +
                ", participantID=" + participantID +
                ", eventType='" + eventType + '\'' +
                ", eventData='" + eventData + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
