package com.tiagojacomelli.bike4life.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

abstract class BaseUseCase {

    private static final String USERS_DB = "users";
    private static final String EVENTS_DB = "events";

    private FirebaseDatabase fbInstance = FirebaseDatabase.getInstance();

    DatabaseReference usersReference = fbInstance.getReference(USERS_DB);
    DatabaseReference eventsReference = fbInstance.getReference(EVENTS_DB);
}
