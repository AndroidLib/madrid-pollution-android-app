package com.greenlionsoft.pollution.madrid.dependencies.gcm;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.greenlionsoft.pollution.madrid.BuildConfig;
import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;
import com.greenlionsoft.pollution.madrid.tools.JodaUtil;
import com.greenlionsoft.pollution.madrid.tools.ResourceUtil;
import com.greenlionsoft.pollution.madrid.ui.activities.PollutionAlertActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import cloudmessaging.ICloudMessaging;
import datasources.userpreferences.IUserPreferencesRepository;
import entities.TimePatterns;

public class CustomGcmListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        //only valid data will be processed
        if (!isValidData(data)) {
            return;
        }

        IUserPreferencesRepository userPreferences = AppInjector.getInstance().getUserPreferencesRepository();

        String type = data.getString(ICloudMessaging.TYPE);

        if (type.equals(ICloudMessaging.TRAFFIC_RESTRICTION_ALERT)) {

            int receivedScenarioLevel = Integer.parseInt(data.getString(ICloudMessaging.LEVEL));
            String receivedAlertDate = data.getString(ICloudMessaging.DATE);
            DateTime receivedAlertDateTime = JodaUtil.JodaStringToDateTime(receivedAlertDate, TimePatterns.ALERT_PATTERN);

            int currentScenarioLevel = userPreferences.getPollutionAlertScenarioLevel();
            String currentDate = userPreferences.getPollutionAlertDate();
            DateTime currentTime = JodaUtil.JodaStringToDateTime(currentDate, TimePatterns.ALERT_PATTERN);

            if (!receivedAlertDateTime.isEqual(currentTime)) {
                //Alert date is different, save data and notify user
                userPreferences.savePollutionAlertDate(receivedAlertDate);
                userPreferences.savePollutionAlertScenarioLevel(receivedScenarioLevel);
                showPollutionAlertNotification(receivedScenarioLevel);

            } else if (receivedAlertDateTime.isEqual(currentTime) && receivedScenarioLevel != currentScenarioLevel) {
                //Alert is for same day but level has been updated, save level and notify user
                userPreferences.savePollutionAlertScenarioLevel(receivedScenarioLevel);
                showPollutionAlertNotification(receivedScenarioLevel);
            }
        }

    }


    /**
     * Sanitizes received Push Data
     *
     * @param data
     * @return
     */
    private boolean isValidData(Bundle data) {


        if (data.containsKey(ICloudMessaging.DEBUG_ONLY)) {
            //if debug_only flag is set to true in GCM data, release mode apks will ignore received message

            boolean isDebugApk = BuildConfig.DEBUG;
            boolean isDebugOnlyMessage = Boolean.parseBoolean(data.getString(ICloudMessaging.DEBUG_ONLY));

            if (isDebugOnlyMessage && !isDebugApk) {
                return false;
            }
        }

        if (null == data || !data.containsKey(ICloudMessaging.TYPE)) {
            return false;
        }

        String type = data.getString(ICloudMessaging.TYPE);

        if (null == type && type.isEmpty()) {
            return false;
        }

        if (type.equals(ICloudMessaging.TRAFFIC_RESTRICTION_ALERT)) {

            if (!data.containsKey(ICloudMessaging.LEVEL)
                    || !data.containsKey(ICloudMessaging.DATE)) {
                return false;
            }

            try {
                Integer.parseInt(data.getString(ICloudMessaging.LEVEL));
            } catch (NumberFormatException e) {
                return false;
            }

            try {
                DateTimeFormat.forPattern(TimePatterns.ALERT_PATTERN).parseDateTime(data.getString(ICloudMessaging.DATE));
            } catch (IllegalArgumentException e) {
                return false;
            }

            return true;

        } else {
            return false;
        }
    }


    /**
     * Creates a "BigText" notification upon a given scenario level that is only shown is
     * user not opt-out
     *
     * @param scenarioLevel
     */
    private void showPollutionAlertNotification(int scenarioLevel) {

        if (!AppInjector.getInstance().getUserPreferencesRepository().arePollutionAlertsEnabled()) {
            return;
        }

        String title = getString(R.string.traffic_restriction_alert);
        String message = ResourceUtil.getStringFromId(this, "scenario_type_" + String.valueOf(scenarioLevel));

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, PollutionAlertActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentTitle(title)
                .setTicker(title)
                .setSmallIcon(R.drawable.ic_directions_car_24dp)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setVibrate(new long[]{0, 200, 200, 200, 200, 200})
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_list, getString(R.string.notification_show_details), pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }
}
