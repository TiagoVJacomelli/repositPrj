package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.EventListView;
import com.tiagojacomelli.bike4life.models.Event;
import com.tiagojacomelli.bike4life.models.User;

import java.util.ArrayList;

public class GetEventsUseCase extends BaseUseCase {

    private EventListView eventListView;

    public GetEventsUseCase(EventListView eventListView) {
        this.eventListView = eventListView;
    }

    public void getEventList() {

        eventsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Event> list = new ArrayList<>();

                for (DataSnapshot eventItem: dataSnapshot.getChildren()) {
                    list.add(eventItem.getValue(Event.class));
                }

                eventListView.setEvents(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventListView.eventsFailure("Erro ao recuperar os eventos");
            }
        });
    }

}
