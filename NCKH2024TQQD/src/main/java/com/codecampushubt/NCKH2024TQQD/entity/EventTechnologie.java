package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EventTechnologies")
public class EventTechnologie {

    @Id
    @Column(name = "EventID", nullable = false)
    private Long eventID;

    @Id
    @Column(name = "Technology", length = 100, nullable = false)
    private String technology;

    // Constructor mặc định
    public EventTechnologie() {
    }

    // Getters và Setters

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    // toString method để in thông tin
    @Override
    public String toString() {
        return "EventTechnologies{" +
                "eventID=" + eventID +
                ", technology='" + technology + '\'' +
                '}';
    }
}
