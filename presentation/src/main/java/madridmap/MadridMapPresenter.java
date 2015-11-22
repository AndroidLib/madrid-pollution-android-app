package madridmap;

import entities.PollutantInfo;
import entities.PollutionData;

public class MadridMapPresenter implements IMadridMapUseCaseDelegate {

    private IMadridMapView mView;

    private MadridMapUseCase mMadridMapUseCase;

    private boolean mIsContentExpanded = false;
    private boolean mIsPollutantInfoExpanded = false;
    private boolean mIsShowingPollutionStationsWithData = false;

    public MadridMapPresenter(IMadridMapView view) {
        this.mView = view;
        this.mMadridMapUseCase = new MadridMapUseCase(this,
                view.getAppInjector().getStationsRepository(),
                view.getAppInjector().getUserPreferencesRepository(),
                view.getAppInjector().getPollutantsRepository(),
                view.getAppInjector().getPermissionsRepository());
    }


    public void onMapReady() {
        mView.showProgress();
        mView.populateMapWithPollutionData(mMadridMapUseCase.getEmptyMadridCityPollutionStations(), mMadridMapUseCase.getSelectedStationName());
        mView.populatePollutionDataDate("---- -- --", "--:--");
        mMadridMapUseCase.getNewPollutionDataFromServer();

        if (mMadridMapUseCase.hasAppPermissionToShowUserLocation()) {
            mView.showUserLocation();
        } else {
            mView.requestLocationPermissionToUserIfNecessary();
        }
    }

    public void onContentActionPressed() {

        if (mIsContentExpanded) {
            mView.collapseContent();
        } else {
            mView.expandContent();
        }

        mIsContentExpanded = !mIsContentExpanded;
    }

    public void onPollutantNamePressed(String pollutantName) {

        PollutantInfo pollutantInfo = mMadridMapUseCase.getPollutantInfoFromPollutantName(pollutantName);

        if (mIsPollutantInfoExpanded) {

            mView.onPollutantInfoChanged(pollutantInfo);

        } else {
            mView.expandPollutantInfo(pollutantInfo);
            mIsPollutantInfoExpanded = true;
        }
    }

    public void onCollapsePollutantPressed() {

        mView.collapsePollutantInfo();
        mIsPollutantInfoExpanded = false;
    }

    public void onStationPressed(String name) {

        if (!mIsContentExpanded && mIsShowingPollutionStationsWithData) {
            mView.expandContent();
            mIsContentExpanded = !mIsContentExpanded;
        }

        mMadridMapUseCase.saveSelectedStationName(name);
    }

    public void onUserLocationPermissionGranted() {
        mView.showUserLocation();
    }

    @Override
    public void onPollutionDataAvailable(PollutionData pollutionData, String selectedStationName) {

        mIsShowingPollutionStationsWithData = true;

        mView.hideProgress();
        mView.populateMapWithPollutionData(pollutionData.stations, selectedStationName);
        mView.populatePollutionDataDate(pollutionData.pollutionDate, pollutionData.pollutionTime);
    }

    @Override
    public void onUnKnownError() {
        mView.hideProgress();
        mView.showErrorMessage();
    }

    @Override
    public void onNetWorkFail() {

        mView.hideProgress();
        mView.showNetworkErrorMessage();
    }


}
