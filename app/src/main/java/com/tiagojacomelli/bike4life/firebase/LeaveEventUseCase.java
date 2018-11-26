package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;
import com.tiagojacomelli.bike4life.models.EventUser;

public class LeaveEventUseCase extends BaseUseCase {

    private EventDetailsView eventDetailsView;

    public LeaveEventUseCase(EventDetailsView eventDetailsView) {
        this.eventDetailsView = eventDetailsView;
    }

    public void leaveEvent(EventUser eventUser) {
        eventsReference.child(eventUser.getEventId()).child(PARTICIPANTS).child(eventUser.getParticipantId()).setValue(null);
        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventDetailsView.onEventResult("Você saiu do evento");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventDetailsView.setError("Erro ao tentar sair do evento");
            }
        });
    }
}
