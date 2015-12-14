package com.greenlionsoft.pollution.madrid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;
import com.greenlionsoft.pollution.madrid.tools.JodaUtil;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import dependencies.IAppInjector;
import entities.TimePatterns;
import pollutionalert.IPollutionAlertView;
import pollutionalert.PollutionAlertPresenter;

public class PollutionAlertActivity extends BaseActivity implements IPollutionAlertView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.sw_pollution_alert)
    SwitchCompat mAlertSw;

    @Bind(R.id.rl_content_alert_1)
    RelativeLayout mContentAlert1Rl;

    @Bind(R.id.rl_content_alert_2)
    RelativeLayout mContentAlert2Rl;

    @Bind(R.id.rl_content_alert_3)
    RelativeLayout mContentAlert3Rl;

    @Bind(R.id.rl_content_alert_4)
    RelativeLayout mContentAlert4Rl;

    @Bind(R.id.tv_scenario_title)
    TextView mScenarioTitleTv;

    @Bind(R.id.tv_pollution_alert_disclaimer)
    TextView mPollutionAlertDisclaimerTv;


    private PollutionAlertPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution_alert);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_pollution_alert);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new PollutionAlertPresenter(this);

        mAlertSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.onPollutionAlertsEnabled();
                } else {
                    mPresenter.onPollutionAlertsDisabled();
                }
            }
        });

        mPollutionAlertDisclaimerTv.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(R.anim.activity_stay, R.anim.slide_out_right);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainListMenuActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainListMenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mPresenter.onNewPollutionNotification();
    }

    @Override
    public void showPollutionAlertsEnabled() {
        mAlertSw.setText(getResources().getString(R.string.pollution_alerts_enabled));
        mAlertSw.setChecked(true);
    }

    @Override
    public void showPollutionAlertsDisabled() {
        mAlertSw.setText(getResources().getString(R.string.pollution_alerts_disabled));
        mAlertSw.setChecked(false);
    }

    @Override
    public void showScenario1Alert() {
        hideAllScenarioAlertContent();
        mContentAlert1Rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void showScenario2Alert() {
        hideAllScenarioAlertContent();
        mContentAlert2Rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void showScenario3Alert() {
        hideAllScenarioAlertContent();
        mContentAlert3Rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void showScenario4Alert() {
        hideAllScenarioAlertContent();
        mContentAlert4Rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void showScenarioTitle(int scenarioLevel, String yyyyMMdd_HHmm) {

        DateTime alertTime = JodaUtil.JodaStringToDateTime(yyyyMMdd_HHmm, TimePatterns.ALERT_PATTERN);
        String displayTime = JodaUtil.JodaDateTimeToString(alertTime, TimePatterns.DISPLAY_PATTERN);

        mScenarioTitleTv.setText(String.format(getResources().getString(R.string.activated_scenario_protocol), scenarioLevel, displayTime));
    }

    @Override
    public void showNoScenarioTitle() {
        mScenarioTitleTv.setText(getString(R.string.scenario_type_0));
        hideAllScenarioAlertContent();
    }

    @Override
    public IAppInjector getAppInjector() {
        return AppInjector.getInstance();
    }


    private void hideAllScenarioAlertContent() {
        mContentAlert1Rl.setVisibility(View.GONE);
        mContentAlert2Rl.setVisibility(View.GONE);
        mContentAlert3Rl.setVisibility(View.GONE);
        mContentAlert4Rl.setVisibility(View.GONE);
    }
}
