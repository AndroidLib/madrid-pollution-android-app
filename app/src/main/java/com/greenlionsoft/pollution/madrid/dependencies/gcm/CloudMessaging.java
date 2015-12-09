package com.greenlionsoft.pollution.madrid.dependencies.gcm;


import android.content.Context;
import android.content.Intent;

import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import cloudmessaging.ICloudMessaging;

public class CloudMessaging implements ICloudMessaging {


    private Context mContext;

    public CloudMessaging() {
        this.mContext = AppInjector.getInstance().getApplicationContext();
    }

    @Override
    public void register() {
        Intent intentService = new Intent(mContext, GcmRegistrationIntentService.class);
        mContext.startService(intentService);
    }
}
