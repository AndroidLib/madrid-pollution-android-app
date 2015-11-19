package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;
import android.content.SharedPreferences;

import repository.IUserPreferencesRepository;

public class UserPreferencesRepository implements IUserPreferencesRepository {


    private static final String MADRID_CITY_POLLUTION_DATE = "MADRID_CITY_POLLUTION_DATE";
    private static final String MADRID_CITY_NAME_OF_SELECTED_STATION = "MADRID_CITY_NAME_OF_SELECTED_STATION";


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
}
