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

    public void registerUser(User user) {

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userName/
        String userId = usersReference.push().getKey();

        // pushing user to 'users' node using the userId
        if (userId != null) {
            usersReference.child(user.getUserName()).setValue(user);

            usersReference.child(user.getUserName())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userRegisterView.registerSuccess();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            userRegisterView.registerError();
                        }
                    });
        }
    }
}
