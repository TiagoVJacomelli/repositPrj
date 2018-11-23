package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;
import com.tiagojacomelli.bike4life.models.Event;

public class GetSingleEventUseCase extends BaseUseCase {

    private EventDetailsView eventDetailsView;

    public GetSingleEventUseCase(EventDetailsView eventDetailsView) {
        this.eventDetailsView = eventDetailsView;
    }

    public void getEvent(String eventId) {
        eventsReference.child(eventId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Event event = dataSnapshot.getValue(Event.class);
                        if (event != null ) {
                            eventDetailsView.setEvent(event);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        eventDetailsView.setError("Erro ao carregar evento");
                    }
                });
    }

}
