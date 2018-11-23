package com.tiagojacomelli.bike4life.implementations;

import com.tiagojacomelli.bike4life.models.Event;

public interface EventDetailsView {
    void setEvent(Event event);
    void onDeleteEvent();
    void setError(String message);
}
