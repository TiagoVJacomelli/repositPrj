package com.tiagojacomelli.bike4life.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.models.EventUser;

import java.util.ArrayList;

public class EventParticipantsDialog extends Dialog {

    private ArrayList<EventUser> list;

    private Button dismissButton;
    private TextView users;

    public EventParticipantsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.event_participants_dialog_layout);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        users = findViewById(R.id.eventUsersText);
        dismissButton = findViewById(R.id.dismissButton);
    }

    private void bindDialog() {
        StringBuilder usersText = new StringBuilder();
        for (EventUser user : list) {
            usersText.append(user.getParticipantName()).append("\n");
        }

        users.setText(usersText.toString());

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setList(ArrayList<EventUser> list) {
        this.list =list;
        bindDialog();
    }
}
