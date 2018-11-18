package com.tiagojacomelli.bike4life;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserRegisterActivity extends AppCompatActivity {

    Button salvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        salvar = findViewById(R.id.btn_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserRegisterActivity.this, "Dados salvos com sucesso",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}