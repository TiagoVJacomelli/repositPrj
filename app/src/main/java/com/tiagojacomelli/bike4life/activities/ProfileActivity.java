package com.tiagojacomelli.bike4life.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.firebase.LogoutUseCase;
import com.tiagojacomelli.bike4life.implementations.LogoutView;
import com.tiagojacomelli.bike4life.models.User;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, LogoutView {

    private LogoutUseCase logoutUseCase = new LogoutUseCase(this);

    private TextView userName;
    private TextView name;
    private TextView email;
    private TextView phoneNumber;
    private TextView genre;
    private TextView userLevel;

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.profile_user_name);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        phoneNumber = findViewById(R.id.profile_phone_number);
        genre = findViewById(R.id.profile_genre);
        userLevel = findViewById(R.id.profile_user_level);

        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this);

        setUserInfo();
    }

    private void setUserInfo() {
        User user = ApplicaationPreferences.getUser();

        String userNameText = "Nome de Usuario: " + user.getUserName();
        String nameText = "Nome: " + user.getName();
        String emailText = "E-Mail" + user.getEmail();
        String phoneNumberText = "Telefone de Contato: " + user.getPhoneNumber();
        String genreText = "Gênero: " + user.getGenre();
        String userLevelText = "Nivelamento: " + user.getLevel();

        userName.setText( userNameText );
        name.setText( nameText );
        email.setText( emailText );
        phoneNumber.setText( phoneNumberText );
        genre.setText( genreText );
        userLevel.setText( userLevelText );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button: {
                logoutUseCase.userLogout();
            }
        }
    }

    @Override
    public void logoutResponse() {
        finish();
    }
}
