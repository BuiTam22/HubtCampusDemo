package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "CallParticipants")
public class CallParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CallParticipantID")
    private Long callParticipantId;

//    @ManyToOne
//    @JoinColumn(name = "CallID")
//    private Call call;
//
//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private User user;

    @Column(name = "JoinTime")
    private LocalDateTime joinTime;

    @Column(name = "LeaveTime")
    private LocalDateTime leaveTime;

    @Column(name = "Status")
    private String status;

    @Column(name = "DeviceInfo", length = 255)
    private String deviceInfo;

    @Column(name = "NetworkQuality")
    private String networkQuality;
    public CallParticipant() {}
    public CallParticipant(Long callParticipantId, LocalDateTime joinTime, LocalDateTime leaveTime, String status, String deviceInfo, String networkQuality) {
        this.callParticipantId = callParticipantId;
        this.joinTime = joinTime;
        this.leaveTime = leaveTime;
        this.status = status;
        this.deviceInfo = deviceInfo;
        this.networkQuality = networkQuality;
    }

    public Long getCallParticipantId() {
        return callParticipantId;
    }

    public void setCallParticipantId(Long callParticipantId) {
        this.callParticipantId = callParticipantId;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNetworkQuality() {
        return networkQuality;
    }

    public void setNetworkQuality(String networkQuality) {
        this.networkQuality = networkQuality;
    }


}
