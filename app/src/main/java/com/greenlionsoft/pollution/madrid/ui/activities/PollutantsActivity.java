package com.greenlionsoft.pollution.madrid.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
        getSupportActionBar().setTitle(R.string.title_menu_pollutants);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPollutantsRv.setLayoutManager(linearLayoutManager);

        PollutantsAdapter adapter = new PollutantsAdapter(this, linearLayoutManager);
        mPollutantsRv.setAdapter(adapter);
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
            finish();
            return true;
        }
        return false;
    }
}
