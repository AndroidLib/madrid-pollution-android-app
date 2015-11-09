package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;

import dependencies.IAppInjector;
import pdf.IPdfRepository;

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
}
