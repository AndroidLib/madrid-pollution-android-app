package com.greenlionsoft.pollution.madrid;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import io.fabric.sdk.android.Fabric;

public class BaseApplication extends Application {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dependency Injector
        AppInjector.getInstance().setApplicationContext(getApplicationContext());

        //Fabric stuff
        Fabric.with(this, new Crashlytics());

        //Google Analytics
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker(getString(R.string.analytics_tracking_id));
        //VERY IMPORTANT disable ExceptionReporting to avoid exceptions disappearing from Adb Log!!!
        tracker.enableExceptionReporting(false);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

    }
}
