package com.greenlionsoft.pollution.madrid;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import io.fabric.sdk.android.Fabric;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        AppInjector.getInstance().setApplicationContext(getApplicationContext());

    }
}
