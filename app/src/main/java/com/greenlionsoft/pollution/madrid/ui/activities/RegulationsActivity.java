package com.greenlionsoft.pollution.madrid.ui.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import dependencies.IAppInjector;
import regulations.IRegulationsView;
import regulations.RegulationsPresenter;

public class RegulationsActivity extends BaseActivity implements IRegulationsView {

    @Bind(R.id.tv_boe)
    TextView mBoeTv;

    @Bind(R.id.tv_directive)
    TextView mDirectiveTv;

    @Bind(R.id.tv_no2_regulation)
    TextView mNo2RegulationTv;

    @Bind(R.id.ib_boe_download)
    ImageButton mBoeDownloadBt;

    @Bind(R.id.ib_directive_download)
    ImageButton mDirectiveDownloadBt;

    @Bind(R.id.ib_no2_regulation_download)
    ImageButton mNo2RegulationDownloadBt;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.cl_coordinator)
    CoordinatorLayout mCoordinatorLayout;

    @Bind(R.id.rl_boe_area)
    RelativeLayout mBoeAreaRl;

    @Bind(R.id.rl_directive_area)
    RelativeLayout mDirectiveAreaRl;

    @Bind(R.id.rl_no2_regulation_area)
    RelativeLayout mNo2RegulationAreaRl;

    private RegulationsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulations);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(R.string.title_menu_regulations);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new RegulationsPresenter(this);

        View.OnClickListener directiveClickListener = new DirectiveClickListener();
        View.OnClickListener boeClickListener = new BoeClickListener();
        View.OnClickListener no2RegulationClickListener = new No2RegulationClickListener();

        MaterialRippleLayout.on(mDirectiveAreaRl)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(getResources().getColor(R.color.mp_grey_light))
                .rippleHover(true)
                .create()
                .setOnClickListener(directiveClickListener);

        MaterialRippleLayout.on(mBoeAreaRl)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(getResources().getColor(R.color.mp_grey_light))
                .rippleHover(true)
                .create()
                .setOnClickListener(boeClickListener);

        MaterialRippleLayout.on(mNo2RegulationAreaRl)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(getResources().getColor(R.color.mp_grey_light))
                .rippleHover(true)
                .create()
                .setOnClickListener(no2RegulationClickListener);

        mDirectiveDownloadBt.setOnClickListener(directiveClickListener);
        mBoeDownloadBt.setOnClickListener(boeClickListener);
        mNo2RegulationDownloadBt.setOnClickListener(no2RegulationClickListener);


    }


    private class DirectiveClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPresenter.onDirectivePressed();
        }
    }

    private class BoeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPresenter.onBoePressed();
        }
    }

    private class No2RegulationClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPresenter.onNo2RegulationPressed();
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


    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(R.anim.activity_stay, R.anim.slide_out_right);
        }
    }

    @Override
    public void showBoeDownload() {
        mBoeDownloadBt.setImageResource(R.drawable.ic_file_download);
        mBoeTv.setText(R.string.download_boe);
    }

    @Override
    public void showBoeRead() {
        mBoeDownloadBt.setImageResource(R.drawable.ic_find_in_page);
        mBoeTv.setText(R.string.browse_boe);
    }

    @Override
    public void showDirectiveDownload() {
        mDirectiveDownloadBt.setImageResource(R.drawable.ic_file_download);
        mDirectiveTv.setText(R.string.download_directive);
    }

    @Override
    public void showDirectiveRead() {
        mDirectiveDownloadBt.setImageResource(R.drawable.ic_find_in_page);
        mDirectiveTv.setText(R.string.browse_directive);
    }

    @Override
    public void showNo2RegulationDownload() {
        mNo2RegulationDownloadBt.setImageResource(R.drawable.ic_file_download);
        mNo2RegulationTv.setText(R.string.download_no2_regulation);
    }

    @Override
    public void showNo2RegulationRead() {
        mNo2RegulationDownloadBt.setImageResource(R.drawable.ic_find_in_page);
        mNo2RegulationTv.setText(R.string.browse_no2_regulation);
    }

    @Override
    public void showPdfFile(File pdfFile) {

        Uri pdfPath = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(pdfPath, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Snackbar.make(mCoordinatorLayout, R.string.snackbar_no_pdf_viewer_available, Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public IAppInjector getAppInjector() {
        return AppInjector.getInstance();
    }
}
