package com.greenlionsoft.pollution.madrid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;
import com.greenlionsoft.pollution.madrid.tools.AnalyticsUtil;
import com.greenlionsoft.pollution.madrid.ui.listadapters.MainListMenuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import dependencies.IAppInjector;
import mainlistmenu.IMainListMenuView;
import mainlistmenu.MainListMenuPresenter;

public class MainListMenuActivity extends BaseActivity implements IMainListMenuView {

    private static final int DELAY_FOR_REVEAL_ANIMATION_MS = 100;
    private static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 2015;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListMenuRv.setLayoutManager(linearLayoutManager);

        mPresenter = new MainListMenuPresenter(this);

        MainListMenuAdapter adapter = new MainListMenuAdapter(mPresenter, this);
        mListMenuRv.setAdapter(adapter);

        AnalyticsUtil.countVisit(this, AnalyticsUtil.MAIN_SCREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_list_menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_pollution_alert:
                mPresenter.onPollutionAlertPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
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

    @Override
    public void proceedPollutionAlertView() {
        Intent intent = new Intent(this, PollutionAlertActivity.class);
        startActivityWithTransition(intent);
    }

    @Override
    public void notifyDeviceCompatible() {
        //Not used
    }

    @Override
    public void notifyUserFix() {

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
            GooglePlayServicesUtil.getErrorDialog(status, this,
                    REQUEST_CODE_RECOVER_PLAY_SERVICES).show();
        }

    }

    @Override
    public void notifyDeviceNotCompatible() {
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_not_compatible_title)
                .content(R.string.dialog_not_compatible_content)
                .positiveText(R.string.dialog_ok)
                .iconRes(R.drawable.ic_warning_black_24dp)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        finish();
                    }
                })
                .show();
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

    @Override
    public IAppInjector getAppInjector() {
        return AppInjector.getInstance();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_RECOVER_PLAY_SERVICES:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, R.string.toast_newer_gpservices_version_needed,
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
