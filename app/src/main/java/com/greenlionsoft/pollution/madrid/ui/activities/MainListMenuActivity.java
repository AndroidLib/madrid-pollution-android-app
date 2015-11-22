package com.greenlionsoft.pollution.madrid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.tools.AnalyticsUtil;
import com.greenlionsoft.pollution.madrid.ui.listadapters.MainListMenuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import mainlistmenu.IMainListMenuView;
import mainlistmenu.MainListMenuPresenter;

public class MainListMenuActivity extends BaseActivity implements IMainListMenuView {

    private static final int DELAY_FOR_REVEAL_ANIMATION_MS = 200;

    @Bind(R.id.rv_list_menu)
    RecyclerView mListMenuRv;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private MainListMenuPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_menu);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPresenter = new MainListMenuPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListMenuRv.setLayoutManager(linearLayoutManager);

        MainListMenuAdapter adapter = new MainListMenuAdapter(mPresenter, this);
        mListMenuRv.setAdapter(adapter);


        AnalyticsUtil.countVisit(this, AnalyticsUtil.MAIN_SCREEN);
    }

    @Override
    public void proceedToMapCityView() {
        Intent intent = new Intent(MainListMenuActivity.this, MadridMapActivity.class);
        startActivityWithTransition(intent);
    }

    @Override
    public void proceedToRegulationsView() {
        Intent intent = new Intent(this, RegulationsActivity.class);
        startActivityWithTransition(intent);
    }

    @Override
    public void proceedToPollutantsView() {
        Intent intent = new Intent(this, PollutantsActivity.class);
        startActivityWithTransition(intent);
    }

    private void startActivityWithTransition(final Intent intent) {

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.activity_stay);
            }
        }, DELAY_FOR_REVEAL_ANIMATION_MS);
    }
}
