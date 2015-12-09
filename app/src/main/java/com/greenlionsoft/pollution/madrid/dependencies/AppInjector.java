package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;

import com.greenlionsoft.pollution.madrid.dependencies.compatibility.Compatibility;
import com.greenlionsoft.pollution.madrid.dependencies.gcm.CloudMessaging;

import cloudmessaging.ICloudMessaging;
import compatibility.ICompatibility;
import datasources.pdf.IPdfRepository;
import datasources.pollutants.IPollutantsRepository;
import datasources.stations.IStationsRepository;
import datasources.userpreferences.IUserPreferencesRepository;
import dependencies.IAppInjector;
import permissions.IPermissionsRepository;

public class AppInjector implements IAppInjector {

    private volatile static AppInjector instance;

    private Context mApplicationContext;

    private AppInjector() {

    }

    public static AppInjector getInstance() {

        if (instance == null) {

            synchronized (AppInjector.class) {
                if (instance == null) {
                    instance = new AppInjector();
                }
            }
        }

        return instance;
    }


    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    @Override
    public IPdfRepository getPdfRepository() {
        return new PdfRepository();
    }

    @Override
    public IStationsRepository getStationsRepository() {
        return new StationsRepository();
    }

    @Override
    public IPollutantsRepository getPollutantsRepository() {
        return new PollutantsRepository();
    }

    @Override
    public IUserPreferencesRepository getUserPreferencesRepository() {
        return new UserPreferencesRepository();
    }

    @Override
    public IPermissionsRepository getPermissionsRepository() {
        return new PermissionsRepository();
    }

    @Override
    public ICloudMessaging getCloudMessaging() {
        return new CloudMessaging();
    }

    @Override
    public ICompatibility getCompatibility() {
        return new Compatibility();
    }


}
