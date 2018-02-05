package com.example.ilya.myfirstapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    protected  void onStop(){
        System.out.println("Settings onStop");
//        Communicator.communicator.restart();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        System.out.println("Settings changed: " + key);
        switch (key){
            case "user_name":
                Communicator.communicator.setUser(sharedPreferences.getString(key,""));
                break;
            case "password":
                Communicator.communicator.setPassword(sharedPreferences.getString(key, "123456"));
                break;
            case "ip":
                Communicator.communicator.setIP(sharedPreferences.getString(key, "192.168.1.111"));
                break;
            case "port":
                Communicator.communicator.setPort(sharedPreferences.getInt(key, 4592));
        }
    }
}
