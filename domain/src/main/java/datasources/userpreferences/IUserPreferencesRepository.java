package datasources.userpreferences;

public interface IUserPreferencesRepository {


    String getLastMadridCityPollutionDate();

    void saveLastMadridCityPollutionDate(String date);

    String getSelectedMadridCityNameStation();

    void saveSelectedMadridCityNameStation(String stationName);

    void saveGcmToken(String token);

    String getGcmToken();

}
