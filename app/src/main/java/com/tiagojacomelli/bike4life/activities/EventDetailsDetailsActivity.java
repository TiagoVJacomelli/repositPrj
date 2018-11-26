package com.tiagojacomelli.bike4life.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.firebase.EnterEventUseCase;
import com.tiagojacomelli.bike4life.firebase.GetSingleEventUseCase;
import com.tiagojacomelli.bike4life.firebase.LeaveEventUseCase;
import com.tiagojacomelli.bike4life.firebase.RemoveEventUseCase;
import com.tiagojacomelli.bike4life.implementations.EventDetailsView;
import com.tiagojacomelli.bike4life.models.Event;
import com.tiagojacomelli.bike4life.models.EventUser;
import com.tiagojacomelli.bike4life.utils.EventParticipantsDialog;

public class EventDetailsDetailsActivity extends AppCompatActivity implements EventDetailsView, View.OnClickListener {

    private static final int MY_EVENT = 0;
    private static final int ENTER_EVENT = 1;
    private static final int LEAVE_EVENT = 2;

    private GetSingleEventUseCase eventUseCase = new GetSingleEventUseCase(this);
    private RemoveEventUseCase removeEventUseCase = new RemoveEventUseCase(this);
    private LeaveEventUseCase leaveEventUseCase = new LeaveEventUseCase(this);
    private EnterEventUseCase enterEventUseCase  = new EnterEventUseCase(this);

    private Event mEvent;
    private String eventId;
    private String eventAuthorName;
    private int eventUserReference = 0;
    private int eventTypeButton;

    private TextView eventHeader;
    private TextView eventDate;
    private TextView maxPeople;
    private TextView eventAuthor;
    private TextView eventLevel;
    private TextView eventParticipants;

    private Button eventButton;

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
        eventButton = findViewById(R.id.event_button);
        eventParticipants = findViewById(R.id.event_participants);

        eventButton.setOnClickListener(this);
        eventParticipants.setOnClickListener(this);

        eventUseCase.getEvent(eventId);
    }

    @Override
    public void setEvent(Event event) {

        mEvent = event;
        eventAuthorName = event.getUserName();
        setEventTypeButton(event);

        String myEventButtonText = "Apagar Evento";
        String enterEventButtonText = "Participar do Evento";
        String leaveEventButtonText = "Sair do Evento";

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
        eventParticipants.setPaintFlags(eventParticipants.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        switch (eventTypeButton) {
            case MY_EVENT: {
                eventButton.setText(myEventButtonText);
                break;
            }
            case ENTER_EVENT: {
                eventButton.setText(enterEventButtonText);
                break;
            }
            case LEAVE_EVENT: {
                eventButton.setText(leaveEventButtonText);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.event_button: {
                switch (eventTypeButton) {
                    case MY_EVENT: {
                        removeEventUseCase.deleteEvent(eventId);
                        break;
                    }
                    case ENTER_EVENT: {
                        enterEventUseCase.enterEvent(mEvent);
                        break;
                    }
                    case LEAVE_EVENT: {
                        leaveEventUseCase.leaveEvent( mEvent.getPartcipants().get(eventUserReference) );
                        break;
                    }
                }
                break;
            }

            case R.id.event_participants: {
                showParticipants();
                break;
            }
        }
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private void setEventTypeButton(Event event) {
        String cacheUserName = ApplicaationPreferences.getUser().getName();

        if (eventAuthorName.equals( cacheUserName )) {
            eventTypeButton = MY_EVENT;
        }
        else {
            for (EventUser eventUser : event.getPartcipants()) {
                if ( cacheUserName.equals(eventUser.getParticipantName()) ) {
                    eventUserReference = Integer.parseInt( eventUser.getParticipantId() );
                    eventTypeButton = LEAVE_EVENT;
                    break;
                } else {
                    eventTypeButton = ENTER_EVENT;
                }
            }
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventResult(String message) {
        showMessage(message);
        finish();
    }

    @Override
    public void toMuchRegister(String message) {
        showMessage(message);
    }

    @Override
    public void setError(String message) {
        showMessage(message);
    }

    private void showParticipants(){
        EventParticipantsDialog dialog = new EventParticipantsDialog(this);
        dialog.setList(mEvent.getPartcipants());
        dialog.show();
    }

}
