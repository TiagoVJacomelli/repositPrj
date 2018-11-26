package com.tiagojacomelli.bike4life.implementations;

import com.tiagojacomelli.bike4life.models.Event;

public interface EventDetailsView {
    void setEvent(Event event);
    void onEventResult(String message);
    void toMuchRegister(String message);
    void setError(String message);
}
