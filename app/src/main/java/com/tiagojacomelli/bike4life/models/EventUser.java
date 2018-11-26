package com.tiagojacomelli.bike4life.models;

public class EventUser {

    private String eventId;
    private String participantId;
    private String participantUserName;
    private String participantName;

    public EventUser() { }

    public EventUser(String eventId, String participantId, String participantUserName, String participantName) {
        this.eventId = eventId;
        this.participantId = participantId;
        this.participantUserName = participantUserName;
        this.participantName = participantName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipantUserName() {
        return participantUserName;
    }

    public void setParticipantUserName(String participantUserName) {
        this.participantUserName = participantUserName;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
