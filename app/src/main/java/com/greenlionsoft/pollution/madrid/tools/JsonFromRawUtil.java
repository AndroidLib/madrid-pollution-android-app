package com.greenlionsoft.pollution.madrid.tools;

import android.content.Context;

import com.google.gson.Gson;
import com.greenlionsoft.pollution.madrid.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import entities.PollutionData;


public class JsonFromRawUtil {


    private JsonFromRawUtil() {

    }

    public static PollutionData resourceId(int resourceId, Context context) {

        InputStream raw = context.getResources().openRawResource(R.raw.example);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        PollutionData data = gson.fromJson(rd, PollutionData.class);

        return data;
    }
}
