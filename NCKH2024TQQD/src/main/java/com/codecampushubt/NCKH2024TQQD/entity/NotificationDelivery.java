package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NotificationDelivery")
public class NotificationDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeliveryID", nullable = false)
    private Long deliveryID;

    @Column(name = "NotificationID")
    private Long notificationID;

    @Column(name = "Channel", length = 20, nullable = false)
    private String channel;

    @Column(name = "Status", length = 20, nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SentAt")
    private Date sentAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DeliveredAt")
    private Date deliveredAt;

    @Lob
    @Column(name = "ErrorMessage")
    private String errorMessage;

    public NotificationDelivery() {
    }

    public NotificationDelivery(Long notificationID, String channel, String status, Date sentAt, Date deliveredAt, String errorMessage) {
        this.notificationID = notificationID;
        this.channel = channel;
        this.status = status;
        this.sentAt = sentAt;
        this.deliveredAt = deliveredAt;
        this.errorMessage = errorMessage;
    }

    public Long getDeliveryID() {
        return deliveryID;
    }

    public Long getNotificationID() {
        return notificationID;
    }

    public String getChannel() {
        return channel;
    }

    public String getStatus() {
        return status;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "NotificationDelivery{" +
                "deliveryID=" + deliveryID +
                ", notificationID=" + notificationID +
                ", channel='" + channel + '\'' +
                ", status='" + status + '\'' +
                ", sentAt=" + sentAt +
                ", deliveredAt=" + deliveredAt +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
