package com.tiagojacomelli.bike4life.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.firebase.GetEventsUseCase;
import com.tiagojacomelli.bike4life.implementations.EventListView;
import com.tiagojacomelli.bike4life.models.Event;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements EventListView {

    private GetEventsUseCase getEventsUseCase = new GetEventsUseCase(this);

    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createEvent = findViewById(R.id.create_button);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreatEvent();
            }
        });

        getEventsUseCase.getEventList();
    }

    private void openCreatEvent (){
        Intent intent = new Intent(HomeActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setEvents(ArrayList<Event> list) {

    }

    @Override
    public void eventsFailure(String message) {

    }
}
