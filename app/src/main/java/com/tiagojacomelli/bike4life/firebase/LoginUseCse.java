package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.implementations.LoginView;
import com.tiagojacomelli.bike4life.models.User;

public class LoginUseCse extends BaseUseCase {

    private LoginView loginView;

    public LoginUseCse(LoginView loginView) {
        this.loginView = loginView;
    }

    public void doLogin(String userName, final String password) {
        usersReference.child(userName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // get al user info from database
                        User user = dataSnapshot.getValue(User.class);

                        if (user != null ) {
                            // validate password
                            if (password.equals(user.getPassword())) {
                                ApplicaationPreferences.saveUser(user);
                                ApplicaationPreferences.setLogedStatus(true);
                                loginView.loginSuccess();
                            } else {
                                loginView.loginFailure("Senha incorreta");
                            }

                        } else {
                            loginView.loginFailure("Usuario não cadastrado");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        loginView.loginFailure("Não foi possivél relizar o login");
                    }
                });
    }
}
