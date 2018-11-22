package com.tiagojacomelli.bike4life.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.firebase.LoginUseCse;
import com.tiagojacomelli.bike4life.implementations.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private LoginUseCse loginUseCse = new LoginUseCse(this);

    private EditText email, senha;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        // if user is logged in
        if (ApplicaationPreferences.getUser() != null) {
            goToHome();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId() ) {
            case R.id.btn_login:  {
                setLoginListener();
                break;
            }

            case R.id.btn_register: {
                openRegisterUser();
                break;
            }
        }
    }

    private void  setLoginListener() {
        if (isFieldsEmpty()) {
            Toast.makeText(MainActivity.this, "Preencha todos os campos",Toast.LENGTH_SHORT).show();
        } else {
            login(email.getText().toString(), senha.getText().toString());
        }
    }

    private boolean isFieldsEmpty() {
        return (
                TextUtils.isEmpty(email.getText().toString()) &&
                        TextUtils.isEmpty(senha.getText().toString())
        );
    }

    public void login(String email, String password) {
        System.out.println(email);
        System.out.println(password);

        loginUseCse.doLogin(email, password);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void openRegisterUser() {
        Intent intent = new Intent(this, UserRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccess() {
        goToHome();
    }

    @Override
    public void loginFailure(String message) {
        showMessage(message);
    }
}


