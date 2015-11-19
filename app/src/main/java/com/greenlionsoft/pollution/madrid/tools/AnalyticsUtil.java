package com.greenlionsoft.pollution.madrid.tools;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.greenlionsoft.pollution.madrid.BaseApplication;

public final class AnalyticsUtil {


    private AnalyticsUtil() {
    }

    public static final String MAIN_SCREEN = "MAIN_SCREEN";
    public static final String MADRID_CITY_MAP_SCREEN = "MADID_CITY_MAP_SCREEN";
    public static final String POLLUTANTS_SCREEN = "POLLUTANS_SCREEN";
    public static final String REGULATIONS_SCREEN = "REGULATIONS_SCREEN";


    public static void countVisit(AppCompatActivity activity, String screenName) {

        Tracker tracker = ((BaseApplication) activity.getApplication()).tracker;

        tracker.setScreenName(screenName);

        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static void countVisit(Context context, String screenName) {

        Tracker tracker = ((BaseApplication) ((Activity) context).getApplication()).tracker;

        tracker.setScreenName(screenName);

        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

}
