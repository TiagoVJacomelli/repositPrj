package com.tiagojacomelli.bike4life.firebase;

import com.tiagojacomelli.bike4life.cache.ApplicaationPreferences;
import com.tiagojacomelli.bike4life.implementations.LogoutView;

public class LogoutUseCase {

    private LogoutView logoutView;

    public LogoutUseCase(LogoutView logoutView) {
        this.logoutView = logoutView;
    }

    public void userLogout() {
        ApplicaationPreferences.cleanSharedPref();
        ApplicaationPreferences.setLogedStatus(false);
        logoutView.logoutResponse();
    }
}
