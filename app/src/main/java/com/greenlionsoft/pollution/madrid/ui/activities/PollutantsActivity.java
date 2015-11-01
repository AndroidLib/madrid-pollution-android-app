package com.greenlionsoft.pollution.madrid.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.ui.listadapters.PollutantsAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import pollutants.PollutantsPresenter;

public class PollutantsActivity extends BaseActivity {

    @Bind(R.id.rv_pollutants)
    RecyclerView mPollutantsRv;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private PollutantsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollutants);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPollutantsRv.setLayoutManager(linearLayoutManager);

        PollutantsAdapter adapter = new PollutantsAdapter(this, linearLayoutManager);
        mPollutantsRv.setAdapter(adapter);
    }
}
