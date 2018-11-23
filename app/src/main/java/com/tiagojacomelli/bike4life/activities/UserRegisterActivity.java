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
import com.tiagojacomelli.bike4life.firebase.CreateUserUseCase;
import com.tiagojacomelli.bike4life.implementations.UserRegisterView;
import com.tiagojacomelli.bike4life.models.User;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener, UserRegisterView {

    static int UNCHECKD = -1;

    private CreateUserUseCase createUserUseCase = new CreateUserUseCase(this);

    private EditText userName;
    private EditText name;
    private EditText password;
    private EditText email;
    private EditText phoneNumber;

    private RadioGroup radioGenre;
    private RadioGroup userLevel;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        userName = findViewById(R.id.register_userName);
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        phoneNumber = findViewById(R.id.register_phone_number);
        radioGenre = findViewById(R.id.rdo_genre);
        userLevel = findViewById(R.id.rdo_user_level);

        register = findViewById(R.id.btn_salvar);
        register.setOnClickListener( this );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_salvar: {
                if ( isValidForm() ) registerUser();
                else showMessage("Preencha todos os campos");
            }
        }
    }

    private boolean isValidForm() {
        boolean isValid = true;

        if (userName.getText().toString().isEmpty()) isValid = false;
        if (name.getText().toString().isEmpty()) isValid = false;
        if (email.getText().toString().isEmpty()) isValid = false;
        if (password.getText().toString().isEmpty()) isValid = false;
        if (phoneNumber.getText().toString().isEmpty()) isValid = false;
        if (radioGenre.getCheckedRadioButtonId() == UNCHECKD) isValid = false;
        if (userLevel.getCheckedRadioButtonId() == UNCHECKD) isValid = false;

        return isValid;
    }

    private void registerUser() {

        RadioButton genre = findViewById(radioGenre.getCheckedRadioButtonId());
        RadioButton level = findViewById(userLevel.getCheckedRadioButtonId());

        User user = new User(
                userName.getText().toString(),
                name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                phoneNumber.getText().toString(),
                genre.getText().toString(),
                level.getText().toString()
        );

        createUserUseCase.registerUser(user);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {
        showMessage("Cadastro realizado com sucesso");
        finish();
    }

    @Override
    public void registerError(String message) {
        showMessage(message);
    }
}