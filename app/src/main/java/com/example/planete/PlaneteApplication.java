package com.example.planete;

import android.app.Application;

import io.realm.Realm;

public class PlaneteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
