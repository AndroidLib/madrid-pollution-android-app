package datasources.userpreferences;

public interface IUserPreferencesRepository {


    String getLastMadridCityPollutionDate();

    void saveLastMadridCityPollutionDate(String date);

    String getSelectedMadridCityNameStation();

    void saveSelectedMadridCityNameStation(String stationName);

    void saveGcmToken(String token);

    String getGcmToken();

    boolean arePollutionAlertsEnabled();

    void setPollutionAlertsEnabled();

    void setPollutionAlertsDisabled();

    void savePollutionAlertDate(String yyyyMMdd_HHmm);

    String getPollutionAlertDate();

    void savePollutionAlertScenarioLevel(int level);

    int getPollutionAlertScenarioLevel();

}
