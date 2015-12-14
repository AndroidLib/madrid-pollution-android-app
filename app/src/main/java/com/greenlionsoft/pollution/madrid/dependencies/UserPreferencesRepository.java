package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;
import android.content.SharedPreferences;

import datasources.userpreferences.IUserPreferencesRepository;

public class UserPreferencesRepository implements IUserPreferencesRepository {


    private static final String MADRID_CITY_POLLUTION_DATE = "MADRID_CITY_POLLUTION_DATE";
    private static final String MADRID_CITY_NAME_OF_SELECTED_STATION = "MADRID_CITY_NAME_OF_SELECTED_STATION";
    private static final String GCM_TOKEN = "GCM_TOKEN";
    private static final String POLLUTION_ALERTS = "POLLUTION_ALERTS";
    private static final String POLLUTION_ALERT_DATE = "POLLUTION_ALERT_DATE";
    private static final String POLLUTION_ALERT_SCENARIO_LEVEL = "POLLUTION_ALERT_SCENARIO_LEVEL";


    private Context mContext;
    private SharedPreferences mSharedPreferences;


    public UserPreferencesRepository() {

        this.mContext = AppInjector.getInstance().getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences("pollutionAppPrefs", Context.MODE_PRIVATE);
    }


    @Override
    public String getLastMadridCityPollutionDate() {
        return mSharedPreferences.getString(MADRID_CITY_POLLUTION_DATE, "");
    }

    @Override
    public void saveLastMadridCityPollutionDate(String date) {
        mSharedPreferences.edit().putString(MADRID_CITY_POLLUTION_DATE, date).apply();
    }

    @Override
    public String getSelectedMadridCityNameStation() {
        return mSharedPreferences.getString(MADRID_CITY_NAME_OF_SELECTED_STATION, "");
    }

    @Override
    public void saveSelectedMadridCityNameStation(String stationName) {
        mSharedPreferences.edit().putString(MADRID_CITY_NAME_OF_SELECTED_STATION, stationName).apply();
    }

    @Override
    public void saveGcmToken(String token) {
        mSharedPreferences.edit().putString(GCM_TOKEN, token).apply();
    }

    @Override
    public String getGcmToken() {
        return mSharedPreferences.getString(GCM_TOKEN, "");
    }

    @Override
    public boolean arePollutionAlertsEnabled() {
        return mSharedPreferences.getBoolean(POLLUTION_ALERTS, true);
    }

    @Override
    public void setPollutionAlertsEnabled() {
        mSharedPreferences.edit().putBoolean(POLLUTION_ALERTS, true).apply();
    }

    @Override
    public void setPollutionAlertsDisabled() {
        mSharedPreferences.edit().putBoolean(POLLUTION_ALERTS, false).apply();
    }

    @Override
    public void savePollutionAlertDate(String yyyyMMdd_HHmm) {
        mSharedPreferences.edit().putString(POLLUTION_ALERT_DATE, yyyyMMdd_HHmm).apply();
    }

    @Override
    public String getPollutionAlertDate() {
        return mSharedPreferences.getString(POLLUTION_ALERT_DATE, "19840911_1200");
    }

    @Override
    public void savePollutionAlertScenarioLevel(int level) {
        mSharedPreferences.edit().putInt(POLLUTION_ALERT_SCENARIO_LEVEL, level).apply();
    }

    @Override
    public int getPollutionAlertScenarioLevel() {
        return mSharedPreferences.getInt(POLLUTION_ALERT_SCENARIO_LEVEL, 0);
    }
}
