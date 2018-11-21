package com.tiagojacomelli.bike4life.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.firebase.GetSingleEventUseCase;
import com.tiagojacomelli.bike4life.firebase.RemoveEventUseCase;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;
import com.tiagojacomelli.bike4life.models.Event;

public class EventDetailsDetailsActivity extends AppCompatActivity implements EventDetailsView, View.OnClickListener {

    private String eventId;

    private GetSingleEventUseCase eventUseCase = new GetSingleEventUseCase(this);
    private RemoveEventUseCase removeEventUseCase = new RemoveEventUseCase(this);

    private TextView eventHeader;
    private TextView eventDate;
    private TextView maxPeople;
    private TextView eventAuthor;
    private TextView eventLevel;

    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        eventId = intent.getStringExtra(Event.EVENT_FLAG);

        eventHeader = findViewById(R.id.event_header);
        eventDate = findViewById(R.id.event_data);
        maxPeople = findViewById(R.id.event_limit);
        eventAuthor = findViewById(R.id.event_author);
        eventLevel = findViewById(R.id.event_level);
        deleteButton = findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(this);

        eventUseCase.getEvent(eventId);
    }

    @Override
    public void setEvent(Event event) {

        String eventHeaderText = event.getEventName();
        String eventDateText = "Data: " + event.getEventDate();
        String maxPeopleText = "Limite de Pessoas: " + event.getMaxPeople();
        String eventAuthorText = "Autor do evento: " + event.getUserName();
        String eventLevelText = "Nivelamento: " + event.getEventLevel();

        eventHeader.setText( eventHeaderText );
        eventDate.setText( eventDateText );
        maxPeople.setText( maxPeopleText );
        eventAuthor.setText( eventAuthorText );
        eventLevel.setText( eventLevelText );

        String cacheUserName = ApplicaationPreferences.getUser().getName();
        if (event.getUserName().equals( cacheUserName )) {
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteEvent() {
        finish();
        showMessage("Evento removido");
    }

    @Override
    public void setError(String message) {
        showMessage(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_button: {
                removeEventUseCase.deleteEvent(eventId);
            }
        }
    }
}
