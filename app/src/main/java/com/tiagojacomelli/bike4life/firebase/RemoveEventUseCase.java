package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;

public class RemoveEventUseCase extends BaseUseCase {

    private EventDetailsView eventDetailsView;

    public RemoveEventUseCase(EventDetailsView eventDetailsView) {
        this.eventDetailsView = eventDetailsView;
    }

    public void deleteEvent(String eventId) {

        if (eventId != null) {
            eventsReference.child(eventId).setValue(null);
            eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    eventDetailsView.onDeleteEvent();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    eventDetailsView.setError("Erro ao tentar deletar o evento");
                }
            });
        }
    }

}
