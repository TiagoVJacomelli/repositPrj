package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.RegisterEventView;
import com.tiagojacomelli.bike4life.models.Event;

public class RegisterEventUseCase extends BaseUseCase {

    private RegisterEventView registerEventView;

    public RegisterEventUseCase(RegisterEventView registerEventView) {
        this.registerEventView = registerEventView;
    }

    public void registerEvent(Event event) {

        String eventId = eventsReference.push().getKey();

        if (eventId != null) {
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
