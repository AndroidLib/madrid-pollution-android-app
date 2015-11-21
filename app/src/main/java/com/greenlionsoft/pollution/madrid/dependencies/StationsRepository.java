package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;

import com.greenlionsoft.pollution.madrid.R;
import com.greenlionsoft.pollution.madrid.dependencies.retrofit.PollutionWebService;

import java.util.ArrayList;
import java.util.List;

import entities.PollutionData;
import entities.PollutionStation;
import entities.RawLatLng;
import repository.IStationsRepository;
import repository.IStationsRepositoryDelegate;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StationsRepository implements IStationsRepository {

    private Context mContext;

    private String[] mLatLongArray;
    private String[] mStationsNames;
    private String[] mStationJsonNames;

    private List<RawLatLng> mRawLatLngs;

    private IStationsRepositoryDelegate mDelegate;

    public StationsRepository() {

        mContext = AppInjector.getInstance().getApplicationContext();
        mLatLongArray = mContext.getResources().getStringArray(R.array.array_LatLng_zone_1);
        mStationsNames = mContext.getResources().getStringArray(R.array.array_zone_1);
        mStationJsonNames = mContext.getResources().getStringArray(R.array.array_zone_1_json_names);

        mRawLatLngs = new ArrayList<>();

        for (int i = 0; i < mLatLongArray.length; i++) {

            RawLatLng rawLatLng = new RawLatLng();

            rawLatLng.setLat(Double.parseDouble(mLatLongArray[i].split(",")[0]));
            rawLatLng.setLon(Double.parseDouble(mLatLongArray[i].split(",")[1]));

            mRawLatLngs.add(rawLatLng);
        }

    }

    @Override
    public void setDelegate(IStationsRepositoryDelegate delegate) {
        mDelegate = delegate;
    }


    @Override
    public List<PollutionStation> getEmptyMadridCityPollutionStations() {

        List<PollutionStation> pollutionStations = new ArrayList<>();

        for (int i = 0; i < mLatLongArray.length - 1; i++) {

            PollutionStation pollutionStation = new PollutionStation(
                    mStationsNames[i],
                    Double.parseDouble(mLatLongArray[i].split(",")[0]),
                    Double.parseDouble(mLatLongArray[i].split(",")[1])
            );

            pollutionStations.add(pollutionStation);
        }

        return pollutionStations;
    }



    private void fillLocationData(List<PollutionStation> pollutionStations) {

        for (PollutionStation pollutionStation : pollutionStations) {

            for (int i = 0; i < mStationJsonNames.length; i++) {

                if (pollutionStation.name.equals(mStationJsonNames[i])) {
                    pollutionStation.lat = mRawLatLngs.get(i).getLat();
                    pollutionStation.lon = mRawLatLngs.get(i).getLon();
                    pollutionStation.name = mStationsNames[i];
                }
            }
        }
    }

    public void getPollutionStationsAsync() {

        if (null == mDelegate) {
            throw new IllegalStateException("Delegate can't be null");
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mContext.getResources().getString(R.string.pollution_end_point))
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .build();

        PollutionWebService pollutionWebService = restAdapter.create(PollutionWebService.class);


        pollutionWebService.getPollutionData(new Callback<PollutionData>() {
            @Override
            public void success(PollutionData pollutionData, Response response) {
                fillLocationData(pollutionData.stations);
                mDelegate.onPollutionDataReceived(pollutionData);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                if (retrofitError.getKind() == RetrofitError.Kind.NETWORK) {
                    mDelegate.onNetWorkFail();
                } else {
                    mDelegate.onUnKnownError();
                }
            }
        });


    }
}
