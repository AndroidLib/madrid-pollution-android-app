package com.greenlionsoft.pollution.madrid.dependencies.gcm;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.greenlionsoft.pollution.madrid.R;

public class CustomGcmListenerService extends GcmListenerService {

    private static final String LOG_TAG = "PUSH";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        String message = data.getString("message");
        Log.d(LOG_TAG, "From: " + from);
        Log.d(LOG_TAG, "Message: " + message);
        Log.d(LOG_TAG, data.getString("date"));
        Log.d(LOG_TAG, data.getString("level"));

        showNotification();
    }


    private void showNotification() {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        mBuilder.setContentTitle("Traffic Restriction Alert")
                .setTicker("Restricción de tráfico")
                .setSmallIcon(R.drawable.ic_directions_car_24dp)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setVibrate(new long[]{0, 200, 200, 200, 200, 200})
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText("This the content text");

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }
}
