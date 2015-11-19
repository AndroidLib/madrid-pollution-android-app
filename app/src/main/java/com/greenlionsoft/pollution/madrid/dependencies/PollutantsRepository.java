package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;

import com.greenlionsoft.pollution.madrid.R;

import java.util.HashMap;
import java.util.Map;

import entities.PollutantInfo;
import repository.IPollutantsRepository;

public class PollutantsRepository implements IPollutantsRepository {


    private Context mContext;

    private String[] mPollutantsNames;
    private String[] mPollutantsDescriptions;
    private String[] mPollutantsEffects;

    private Map<String, PollutantInfo> mPollutantInfoMap;


    public PollutantsRepository() {

        mContext = AppInjector.getInstance().getApplicationContext();

        mPollutantsNames = mContext.getResources().getStringArray(R.array.pollutants_names);
        mPollutantsDescriptions = mContext.getResources().getStringArray(R.array.pollutants_descriptions);
        mPollutantsEffects = mContext.getResources().getStringArray(R.array.pollutants_effects);

        mPollutantInfoMap = new HashMap<>();

        for (int i = 0; i < mPollutantsNames.length; i++) {

            PollutantInfo pollutantInfo = new PollutantInfo(mPollutantsNames[i], mPollutantsDescriptions[i], mPollutantsEffects[i]);

            mPollutantInfoMap.put(mPollutantsNames[i], pollutantInfo);
        }
    }

    @Override
    public Map<String, PollutantInfo> getPollutantInfoMap() {
        return mPollutantInfoMap;
    }
}
