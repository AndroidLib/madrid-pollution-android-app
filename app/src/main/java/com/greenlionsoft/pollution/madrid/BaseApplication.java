package com.greenlionsoft.pollution.madrid;

import android.app.Application;

import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppInjector.getInstance().setApplicationContext(getApplicationContext());

    }
}
