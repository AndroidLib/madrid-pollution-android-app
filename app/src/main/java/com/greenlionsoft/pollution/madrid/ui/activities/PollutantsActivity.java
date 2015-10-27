package com.greenlionsoft.pollution.madrid.ui.activities;

import android.os.Bundle;

import com.greenlionsoft.pollution.madrid.R;

import butterknife.ButterKnife;

public class PollutantsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollutants);

        ButterKnife.bind(this);




    }
}
