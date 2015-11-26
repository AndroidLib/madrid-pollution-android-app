package madridmap;

import java.util.List;

import datasources.pollutants.IPollutantsRepository;
import datasources.stations.IStationsRepository;
import datasources.stations.IStationsRepositoryDelegate;
import datasources.userpreferences.IUserPreferencesRepository;
import entities.PollutantInfo;
import entities.PollutionData;
import entities.PollutionStation;
import permissions.IPermissionsRepository;

public class MadridMapUseCase implements IStationsRepositoryDelegate {


    private IMadridMapUseCaseDelegate mDelegate;
    private IStationsRepository mStationsRepository;
    private IUserPreferencesRepository mUserPreferencesRepository;
    private IPollutantsRepository mPollutantsRepository;
    private IPermissionsRepository mPermissionsRepository;


    public MadridMapUseCase(IMadridMapUseCaseDelegate delegate,
                            IStationsRepository stationsRepository,
                            IUserPreferencesRepository preferencesRepository,
                            IPollutantsRepository pollutantsRepository,
                            IPermissionsRepository permissionsRepository) {

        this.mDelegate = delegate;
        this.mStationsRepository = stationsRepository;
        mStationsRepository.setDelegate(this);
        this.mUserPreferencesRepository = preferencesRepository;
        this.mPollutantsRepository = pollutantsRepository;
        this.mPermissionsRepository = permissionsRepository;

    }


    public void getNewPollutionDataFromServer() {
        mStationsRepository.getPollutionStationsAsync();
    }

    public List<PollutionStation> getEmptyMadridCityPollutionStations() {

        return mStationsRepository.getEmptyMadridCityPollutionStations();
    }

    public String getSelectedStationName() {
        return mUserPreferencesRepository.getSelectedMadridCityNameStation();
    }

    public void saveSelectedStationName(String selectedStationName) {
        mUserPreferencesRepository.saveSelectedMadridCityNameStation(selectedStationName);
    }


    public PollutantInfo getPollutantInfoFromPollutantName(String pollutantName) {
        return mPollutantsRepository.getPollutantInfoMap().get(pollutantName);
    }

    public boolean hasAppPermissionToShowUserLocation() {
        return mPermissionsRepository.hasAppPermissionsToShowUserLocation();
    }

    @Override
    public void onPollutionDataReceived(PollutionData pollutionData) {

        mDelegate.onPollutionDataAvailable(pollutionData, getSelectedStationName());
    }

    @Override
    public void onNetWorkFail() {
        mDelegate.onNetWorkFail();
    }

    @Override
    public void onUnKnownError() {
        mDelegate.onUnKnownError();
    }
}
