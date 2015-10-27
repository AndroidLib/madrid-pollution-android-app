package com.greenlionsoft.pollution.madrid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.ui.listadapters.MainListMenuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import mainlistmenu.IMainListMenuView;
import mainlistmenu.MainListMenuPresenter;

public class MainListMenuActivity extends BaseActivity implements IMainListMenuView {

    @Bind(R.id.rv_list_menu)
    RecyclerView mListMenuRv;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private MainListMenuPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_menu_activity);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPresenter = new MainListMenuPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListMenuRv.setLayoutManager(linearLayoutManager);

        MainListMenuAdapter adapter = new MainListMenuAdapter(mPresenter, this);
        mListMenuRv.setAdapter(adapter);

    }

    @Override
    public void proceedToPollutantsView() {
        Intent intent = new Intent(this, PollutantsActivity.class);
        startActivity(intent);
    }
}
