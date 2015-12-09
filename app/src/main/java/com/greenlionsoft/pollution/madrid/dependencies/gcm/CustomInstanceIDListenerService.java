package com.greenlionsoft.pollution.madrid.dependencies.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class CustomInstanceIDListenerService extends InstanceIDListenerService {


    /**
     * When the GCM token assigned to the instance o Google Play services running in
     * our device changes, this method is called and we have to update our registration
     * to the GCM system to keep receiving push messages.
     */
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Intent intent = new Intent(this, GcmRegistrationIntentService.class);
        startService(intent);
    }
}
