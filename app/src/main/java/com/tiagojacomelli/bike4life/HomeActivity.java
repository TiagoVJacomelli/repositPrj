package com.tiagojacomelli.bike4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

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
    }

    private void openCreatEvent (){
        Intent intent = new Intent(HomeActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
