package com.tiagojacomelli.bike4life.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiagojacomelli.bike4life.implementations.UserRegisterView;
import com.tiagojacomelli.bike4life.models.User;

public class CreateUserUseCase extends BaseUseCase {

    private UserRegisterView userRegisterView;

    public CreateUserUseCase(UserRegisterView userRegisterView) {
        this.userRegisterView = userRegisterView;
    }

    public void registerUser(final User user) {

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userName/
        String userId = usersReference.push().getKey();

        // pushing user to 'users' node using the userId
        if (userId != null) {
            usersReference.child(user.getUserName())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User mUser = dataSnapshot.getValue(User.class);
                            if (mUser == null) {
                                usersReference.child(user.getUserName()).setValue(user);
                                userRegisterView.registerSuccess();
                            } else {
                                userRegisterView.registerError("User Name já existe!!!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            userRegisterView.registerError("Não foi possivel realizar o cadastro");
                        }
                    });
        }
    }
}
