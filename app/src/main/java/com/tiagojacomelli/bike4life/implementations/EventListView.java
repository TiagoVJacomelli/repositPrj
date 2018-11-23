package com.tiagojacomelli.bike4life.implementations;

import com.tiagojacomelli.bike4life.models.Event;

import java.util.ArrayList;

public interface EventListView {
    void setEvents(ArrayList<Event> list);
    void eventsFailure(String message);
}
