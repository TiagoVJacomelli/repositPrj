package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.implementations.EventListView;
import com.tiagojacomelli.bike4life.models.Event;

import java.util.ArrayList;

public class GetEventsUseCase extends BaseUseCase {

    private EventListView eventListView;

    public GetEventsUseCase(EventListView eventListView) {
        this.eventListView = eventListView;
    }

    public void getEventList(final boolean isMyEvent) {

        eventsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Event> list = new ArrayList<>();

                for (DataSnapshot eventItem: dataSnapshot.getChildren()) {
                    Event item = eventItem.getValue(Event.class);

                    if (item  != null) {
                        if (isMyEvent && checkEvent( item )) {
                            list.add(eventItem.getValue(Event.class));
                        } else if ( !isMyEvent ) {
                            list.add(eventItem.getValue(Event.class));
                        }
                    }
                }

                eventListView.setEvents(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                eventListView.eventsFailure("Erro ao recuperar os eventos");
            }
        });
    }

    private boolean checkEvent(Event item) {
        return ApplicaationPreferences.getUser().getName().equals(item.getUserName());
    }
}
