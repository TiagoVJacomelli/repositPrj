package com.tiagojacomelli.bike4life.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tiagojacomelli.bike4life.R;
import com.tiagojacomelli.bike4life.models.User;

public class ApplicaationPreferences {
    private static ApplicaationPreferences myInstance = new ApplicaationPreferences();

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static Gson gson = new Gson();

    private static String USER_KEY = "user_prefs";

    private ApplicaationPreferences(){

    }

    public static ApplicaationPreferences getMyInstance(){
        return myInstance;
    }

    /**
     * Inicia os atributos necessarios do gerenciador de preferencias
     * @param context Actvity context
     */
    public static void initMyPrefManager(Context context){
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.mypref), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static void cleanSharedPref() {
        editor.clear(); // Remove old data
    }

    public static void saveUser(User user) {
        String json = gson.toJson(user);
        editor.putString(USER_KEY, json);
        editor.commit();
    }

    public static User getUser() {
        String json = sharedPreferences.getString(USER_KEY, "");
        return gson.fromJson(json, User.class);
    }
}
