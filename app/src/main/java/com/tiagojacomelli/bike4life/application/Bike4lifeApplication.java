package com.tiagojacomelli.bike4life.application;

import android.app.Application;

import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;

public class Bike4lifeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicaationPreferences.initMyPrefManager(this);
    }
}
