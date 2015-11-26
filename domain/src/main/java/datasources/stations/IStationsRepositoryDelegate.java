package datasources.stations;

import entities.PollutionData;

public interface IStationsRepositoryDelegate {


    void onPollutionDataReceived(PollutionData pollutionData);

    void onNetWorkFail();

    void onUnKnownError();


}
