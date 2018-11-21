package com.tiagojacomelli.bike4life.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.firebase.RegisterEventUseCase;
import com.tiagojacomelli.bike4life.implementations.RegisterEventView;
import com.tiagojacomelli.bike4life.models.Event;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterEventView {

    static int UNCHECKD = -1;

    private RegisterEventUseCase registerEventUseCase = new RegisterEventUseCase(this);

    private EditText eventName;
    private EditText eventDate;
    private EditText maxPeople;
    private RadioGroup eventLevel;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eventName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.event_date);
        maxPeople = findViewById(R.id.event_total);
        eventLevel = findViewById(R.id.rdg_activity_level);
        save = findViewById(R.id.btn_register_event);

        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_event: {
                if ( isValidForm() ) registerEvent();
                else showMessage("Preencha todos os campos");
            }
        }
    }

    private boolean isValidForm() {
        boolean isValid = true;

        if (eventName.getText().toString().isEmpty()) isValid = false;
        if (eventDate.getText().toString().isEmpty()) isValid = false;
        if (maxPeople.getText().toString().isEmpty()) isValid = false;
        if (eventLevel.getCheckedRadioButtonId() == UNCHECKD) isValid = false;

        return isValid;
    }

    private void registerEvent() {

        RadioButton selectedLevel = findViewById(eventLevel.getCheckedRadioButtonId());

        Event event = new Event(
                ApplicaationPreferences.getUser().getUserName(),
                eventName.getText().toString(),
                eventDate.getText().toString(),
                maxPeople.getText().toString(),
                selectedLevel.getText().toString()
        );

        registerEventUseCase.registerEvent(event);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {
        showMessage("Evento registrado com sucesso");
        finish();
    }

    @Override
    public void registerError() {
        showMessage("Não foi possivel registrar o evento");
    }
}
