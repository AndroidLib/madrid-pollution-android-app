package datasources.userpreferences;

public interface IUserPreferencesRepository {


    String getLastMadridCityPollutionDate();

    void saveLastMadridCityPollutionDate(String date);

    String getSelectedMadridCityNameStation();

    void saveSelectedMadridCityNameStation(String stationName);


}
