package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;
import com.tiagojacomelli.bike4life.models.Event;
import com.tiagojacomelli.bike4life.models.EventUser;

public class EnterEventUseCase extends BaseUseCase {

    private EventDetailsView eventDetailsView;

    public EnterEventUseCase(EventDetailsView eventDetailsView) {
        this.eventDetailsView = eventDetailsView;
    }

    public void enterEvent(Event event) {

        int eventCur = event.getPartcipants().size() - 1;
        int eventMax = Integer.parseInt( event.getMaxPeople() );

        if (eventCur < eventMax) {
            String eventUserId = Integer.toString( event.getPartcipants().size() );

            EventUser eventUser  = new EventUser(
                    event.getEventId(),
                    eventUserId,
                    ApplicaationPreferences.getUser().getUserName(),
                    ApplicaationPreferences.getUser().getName()
            );

            eventsReference.child(event.getEventId()).child(PARTICIPANTS).child(eventUserId).setValue(eventUser);

            eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    eventDetailsView.onEventResult("Registrado evento");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    eventDetailsView.setError("Erro ao tentar sair do evento");
                }
            });
        } else {
            eventDetailsView.toMuchRegister("Numero máximo atingido");
        }
    }
}
