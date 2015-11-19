package madridmap;

import entities.PollutionData;

public interface IMadridMapUseCaseDelegate {


    void onPollutionDataAvailable(PollutionData pollutionData, String selectedStationName);

    void onNetWorkFail();

    void onUnKnownError();


}
