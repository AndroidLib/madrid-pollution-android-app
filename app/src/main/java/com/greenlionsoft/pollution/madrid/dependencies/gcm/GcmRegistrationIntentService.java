package com.greenlionsoft.pollution.madrid.dependencies.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import java.io.IOException;

import datasources.userpreferences.IUserPreferencesRepository;

public class GcmRegistrationIntentService extends IntentService {

    private static final String LOG_TAG = "GcmRegistration";

    /**
     * We define two topics to register user:
     * <p/>
     * global -> All users are permanently registered to this topic, only used for dev emergencies
     * <p/>
     * pollution_restrictions -> Users can opt-out to get Traffic Restrictions due to Pollution in Madrid
     */
    private static final String[] TOPICS = {"global", "pollution_restrictions"};


    public GcmRegistrationIntentService() {
        super("GcmRegistrationIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        IUserPreferencesRepository userPreferences = AppInjector.getInstance().getUserPreferencesRepository();

        try {

            InstanceID instanceID = InstanceID.getInstance(this);

            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.d("Token", token);

            userPreferences.saveGcmToken(token);

            subscribeToTopics(token);

        } catch (Exception e) {
            Log.e(LOG_TAG, "Registration failed ", e);
        }

    }

    private void subscribeToTopics(String token) throws IOException {

        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }

    }
}
