package madridmap;

import java.util.List;

import baseview.IBaseView;
import entities.PollutantInfo;
import entities.PollutionStation;
import entities.RawLatLng;

public interface IMadridMapView extends IBaseView {

    void drawStations(List<RawLatLng> locations);

    void showProgress();

    void hideProgress();

    void showNetworkErrorMessage();

    void showErrorMessage();

    void populateMapWithPollutionData(List<PollutionStation> pollutionStations, String selectedStationName);

    void expandContent();

    void collapseContent();

    void onPollutantInfoChanged(PollutantInfo pollutantInfo);

    void expandPollutantInfo(PollutantInfo pollutantInfo);

    void collapsePollutantInfo();

    void populatePollutionDataDate(String date, String time);
}
