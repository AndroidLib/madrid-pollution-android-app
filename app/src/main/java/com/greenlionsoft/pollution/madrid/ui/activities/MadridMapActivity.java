package com.greenlionsoft.pollution.madrid.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.AppInjector;
import com.greenlionsoft.pollution.madrid.tools.ScreenUtil;
import com.greenlionsoft.pollution.madrid.tools.ZoomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import dependencies.IAppInjector;
import entities.ColorRules;
import entities.Pollutant;
import entities.PollutantInfo;
import entities.PollutionStation;
import entities.RawLatLng;
import madridmap.IMadridMapView;
import madridmap.MadridMapPresenter;

public class MadridMapActivity extends BaseActivity implements OnMapReadyCallback, IMadridMapView {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.pb_progress)
    ProgressBar mProgressPb;

    @Bind(R.id.fab_content_action)
    FloatingActionButton mContentActionFab;

    @Bind(R.id.fab_pollutant_action)
    FloatingActionButton mCollapsePollutantInfoFab;

    @Bind(R.id.ll_data_holder)
    LinearLayout mDataHolderLl;

    @Bind(R.id.ll_pollutant_content)
    LinearLayout mPollutantInfoHolderLl;

    @Bind(R.id.tv_effects_content)
    TextView mEffectsTv;

    @Bind(R.id.tv_description_content)
    TextView mDescriptionTv;

    @Bind(R.id.tv_pollution_station_name)
    TextView mPollutionStationNameTv;

    @Bind(R.id.tv_so2_value)
    TextView mSO2valueTv;

    @Bind(R.id.tv_co_value)
    TextView mCOvalueTv;

    @Bind(R.id.tv_no2_value)
    TextView mNO2valueTv;

    @Bind(R.id.tv_o3_value)
    TextView mO3valueTv;

    @Bind(R.id.tv_tol_value)
    TextView mTOLvalueTv;

    @Bind(R.id.tv_ben_value)
    TextView mBENvalueTv;

    @Bind(R.id.tv_pm25_value)
    TextView mPM25valueTv;

    @Bind(R.id.tv_pm10_value)
    TextView mPM10valueTv;

    @Bind(R.id.cl_coordinator)
    CoordinatorLayout mCoordinatorCl;

    @Bind(R.id.tv_date)
    TextView mDateTv;

    @Bind(R.id.tv_time)
    TextView mTimeTv;


    private SupportMapFragment mMapFragment;

    private MadridMapPresenter mPresenter;

    private GoogleMap mGoogleMap;

    private Marker mSelectedMarker;
    private Map<String, PollutionStation> mMakerIdsPollutionStationsMap;

    private static final double SOL_LAT = 40.41694400;
    private static final double SOL_LON = -3.703611000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_map);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_menu_madrid_city);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new MadridMapPresenter(this);


        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mContentActionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.onContentActionPressed();
            }
        });

        mCollapsePollutantInfoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onCollapsePollutantPressed();
            }
        });

        initLayout();

        if (savedInstanceState == null) {
            mMapFragment.getMapAsync(this);
        }
    }

    private void initLayout() {

        mCollapsePollutantInfoFab.setVisibility(View.INVISIBLE);
        mPollutantInfoHolderLl.setVisibility(View.INVISIBLE);

        ViewGroup.LayoutParams layoutParams = mDataHolderLl.getLayoutParams();
        layoutParams.height = ScreenUtil.dipsToPixels(this, 40);
        mDataHolderLl.setLayoutParams(layoutParams);
    }

    public void onPollutantNameClick(View view) {

        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            mPresenter.onPollutantNamePressed(tv.getText().toString());
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(SOL_LAT, SOL_LON), 12));

        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                mPresenter.onMapReady();

                mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        setMarkerAsSelected(marker, true);

                        return true;
                    }
                });
            }
        });
    }


    @Override
    public IAppInjector getAppInjector() {
        return AppInjector.getInstance();
    }

    @Override
    public void drawStations(List<RawLatLng> locations) {

        //ZoomUtil.adjustZoomToShowAllLocations(mGoogleMap, locations);


        for (RawLatLng rawLatLng : locations) {

            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(rawLatLng.getLat(), rawLatLng.getLon()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_grey)))
                    .setAnchor(0.5f, 0.5f);
        }


    }

    @Override
    public void showProgress() {
        mProgressPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressPb.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkErrorMessage() {

        Snackbar.make(mCoordinatorCl, R.string.error_network_message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(mCoordinatorCl, R.string.error_unknown_message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void populateMapWithPollutionData(List<PollutionStation> pollutionStations, String selectedStationName) {

        mGoogleMap.clear();

        mMakerIdsPollutionStationsMap = new HashMap<>();

        List<LatLng> locations = new ArrayList<>();

        Marker selectedMarker = null;

        for (PollutionStation pollutionStation : pollutionStations) {

            LatLng location = new LatLng(pollutionStation.lat, pollutionStation.lon);
            locations.add(location);

            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .title(pollutionStation.name)
                    .position(location)
                    .anchor(0.5f, 0.5f));

            setMarkerIcon(marker, pollutionStation, false);

            if (pollutionStation.name.toUpperCase().equals(selectedStationName.toUpperCase())) {
                selectedMarker = marker;
            }

            mMakerIdsPollutionStationsMap.put(marker.getId(), pollutionStation);
        }

        ZoomUtil.adjustZoomToShowAllLocations(mGoogleMap, locations);

        if (null != selectedMarker) {

            setMarkerAsSelected(selectedMarker, false);
        }


    }

    @Override
    public void expandContent() {

        ValueAnimator expandAnim = ValueAnimator.ofInt(ScreenUtil.dipsToPixels(this, 40), ScreenUtil.dipsToPixels(this, 176));

        expandAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mDataHolderLl.getLayoutParams();
                layoutParams.height = val;
                mDataHolderLl.setLayoutParams(layoutParams);
            }
        });
        expandAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        expandAnim.start();


        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(mContentActionFab, "rotation", 0, 180);
        rotateAnimation.start();
    }

    @Override
    public void collapseContent() {

        ValueAnimator collapseAnim = ValueAnimator.ofInt(ScreenUtil.dipsToPixels(this, 176), ScreenUtil.dipsToPixels(this, 40));

        collapseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mDataHolderLl.getLayoutParams();
                layoutParams.height = val;
                mDataHolderLl.setLayoutParams(layoutParams);
            }
        });
        collapseAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        collapseAnim.start();

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(mContentActionFab, "rotation", 180, 0);
        rotateAnimation.start();
    }

    @Override
    public void onPollutantInfoChanged(PollutantInfo pollutantInfo) {

        ViewGroup.LayoutParams params = mPollutantInfoHolderLl.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mPollutantInfoHolderLl.setLayoutParams(params);

        mDescriptionTv.setText(pollutantInfo.getDescription());
        mEffectsTv.setText(pollutantInfo.getEffects());
    }

    @Override
    public void expandPollutantInfo(PollutantInfo pollutantInfo) {

        mDescriptionTv.setText(pollutantInfo.getDescription());
        mEffectsTv.setText(pollutantInfo.getEffects());

        ViewGroup.LayoutParams params = mPollutantInfoHolderLl.getLayoutParams();

        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        mPollutantInfoHolderLl.setLayoutParams(params);

        mPollutantInfoHolderLl.setVisibility(View.INVISIBLE);

        mPollutantInfoHolderLl.post(new Runnable() {
            @Override
            public void run() {

                ValueAnimator expandAnim = ValueAnimator.ofInt(0, mPollutantInfoHolderLl.getHeight());
                expandAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val = (Integer) animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = mPollutantInfoHolderLl.getLayoutParams();
                        layoutParams.height = val;
                        mPollutantInfoHolderLl.setLayoutParams(layoutParams);

                    }
                });
                expandAnim.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mCollapsePollutantInfoFab.setVisibility(View.VISIBLE);
                        mContentActionFab.setVisibility(View.GONE);
                    }

                });
                expandAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                expandAnim.start();
                mPollutantInfoHolderLl.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void collapsePollutantInfo() {

        final int startHeight = mPollutantInfoHolderLl.getHeight();

        ValueAnimator collapseAnim = ValueAnimator.ofInt(startHeight, 0);

        collapseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mPollutantInfoHolderLl.getLayoutParams();
                layoutParams.height = val;
                mPollutantInfoHolderLl.setLayoutParams(layoutParams);
            }
        });

        collapseAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mContentActionFab.setVisibility(View.VISIBLE);
                mCollapsePollutantInfoFab.setVisibility(View.GONE);
            }
        });
        collapseAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        collapseAnim.start();
    }

    @Override
    public void populatePollutionDataDate(String date, String time) {

        mDateTv.setText(date);
        mTimeTv.setText(time);

    }

    private void setMarkerAsSelected(Marker marker, boolean centerMap) {


        if (null != mSelectedMarker) {
            setMarkerIcon(mSelectedMarker, mMakerIdsPollutionStationsMap.get(mSelectedMarker.getId()), false);
        }

        setMarkerIcon(marker, mMakerIdsPollutionStationsMap.get(marker.getId()), true);

        mSelectedMarker = marker;

        fillContentWithStationData(mMakerIdsPollutionStationsMap.get(mSelectedMarker.getId()));

        mPresenter.onStationPressed(mMakerIdsPollutionStationsMap.get(mSelectedMarker.getId()).name);

        if (centerMap) {
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()), 300, null);
        }

    }

    private void fillContentWithStationData(PollutionStation pollutionStation) {

        mPollutionStationNameTv.setText(pollutionStation.name);

        mSO2valueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.SO2, pollutionStation.so2)));
        mNO2valueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.NO2, pollutionStation.no2)));
        mCOvalueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.CO, pollutionStation.co)));
        mO3valueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.O3, pollutionStation.o3)));
        mPM25valueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.PM25, pollutionStation.pm25)));
        mPM10valueTv.setBackgroundColor(getAndroidColorResourceId(ColorRules.getColorForPollutantValue(Pollutant.PM10, pollutionStation.pm10)));
        mTOLvalueTv.setBackgroundColor(getResources().getColor(R.color.mp_grey));
        mBENvalueTv.setBackgroundColor(getResources().getColor(R.color.mp_grey));

        mSO2valueTv.setText(pollutionStation.so2);
        mCOvalueTv.setText(pollutionStation.co);
        mNO2valueTv.setText(pollutionStation.no2);
        mO3valueTv.setText(pollutionStation.o3);
        mTOLvalueTv.setText(pollutionStation.tol);
        mBENvalueTv.setText(pollutionStation.ben);
        mPM25valueTv.setText(pollutionStation.pm25);
        mPM10valueTv.setText(pollutionStation.pm10);


    }

    private int getAndroidColorResourceId(int pollutantColor) {

        switch (pollutantColor) {

            case ColorRules.COLOR_GREEN:
                return getResources().getColor(R.color.colorPrimary);

            case ColorRules.COLOR_YELLOW:
                return getResources().getColor(R.color.mp_yellow);

            case ColorRules.COLOR_RED:
                return getResources().getColor(R.color.mp_red);

            default:
                return getResources().getColor(R.color.mp_grey);
        }


    }


    private void setMarkerIcon(Marker marker, PollutionStation pollutionStation, boolean selected) {

        if (null != pollutionStation) {

            int color = ColorRules.getStationColor(pollutionStation);

            int resourceId = 0;

            switch (color) {

                case ColorRules.COLOR_GREEN:
                    resourceId = selected ? R.drawable.ic_marker_green_selected : R.drawable.ic_marker_green;
                    break;

                case ColorRules.COLOR_YELLOW:
                    resourceId = selected ? R.drawable.ic_marker_yellow_selected : R.drawable.ic_marker_yellow;
                    break;

                case ColorRules.COLOR_RED:
                    resourceId = selected ? R.drawable.ic_marker_red_selected : R.drawable.ic_marker_red;
                    break;

                default:
                    resourceId = selected ? R.drawable.ic_marker_grey_selected : R.drawable.ic_marker_grey;
                    break;
            }

            marker.setIcon(BitmapDescriptorFactory.fromResource(resourceId));
        }

    }
}
