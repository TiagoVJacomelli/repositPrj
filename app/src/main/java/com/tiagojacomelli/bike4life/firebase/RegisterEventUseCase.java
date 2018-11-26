package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.implementations.RegisterEventView;
import com.tiagojacomelli.bike4life.models.Event;
import com.tiagojacomelli.bike4life.models.EventUser;

import java.util.ArrayList;

public class RegisterEventUseCase extends BaseUseCase {

    private RegisterEventView registerEventView;

    public RegisterEventUseCase(RegisterEventView registerEventView) {
        this.registerEventView = registerEventView;
    }

    public void registerEvent(Event event) {
        String eventId = eventsReference.push().getKey();

        if (eventId != null) {
            ArrayList<EventUser> participantList = new ArrayList<>();
            participantList.add( new EventUser(
                    eventId,
                    Integer.toString(participantList.size()),
                    ApplicaationPreferences.getUser().getUserName(),
                    ApplicaationPreferences.getUser().getName()
            ));

            event.setEventId( eventId );
            event.setPartcipants( participantList );

            eventsReference.child(eventId).setValue(event);
            eventsReference.child(eventId)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            registerEventView.registerSuccess();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            registerEventView.registerError();
                        }
                    });
        }
    }
}
