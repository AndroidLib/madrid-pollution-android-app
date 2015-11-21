package com.greenlionsoft.pollution.madrid.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.greenlionsoft.pollution.madrid.R;

public class AdmobFragment extends Fragment {

    private AdView mAdView;


    public AdmobFragment() {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admob, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdView = (AdView) getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("E840AD920F48EA511997C44CC12B9F4E") //Nexus 5X
                .addTestDevice("8B35862904600AD7B9427BDEAFA067FF") //HTC
                .addTestDevice("0C3259C626FA4C0B561A309EE009FB50") //Tablet
                .addTestDevice("688EC78A3BB787C230618C1569F97CD1") //S3
                .addTestDevice("6B9ECFAE97BB66B728B8A7DF1C9D651D") //S5
                .build();
        mAdView.loadAd(adRequest);
    }
}
