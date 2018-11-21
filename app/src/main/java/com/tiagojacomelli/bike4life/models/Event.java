package com.tiagojacomelli.bike4life.models;

public class Event {

    public static final String EVENT_FLAG = "event_flag_id";

    private String eventId;
    private String userName;
    private String eventName;
    private String eventDate;
    private String maxPeople;
    private String eventLevel;

    public Event() {  }

    public Event(String userName, String eventName, String eventDate, String maxPeople, String eventLevel) {
        this.userName = userName;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.maxPeople = maxPeople;
        this.eventLevel = eventLevel;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }
}
