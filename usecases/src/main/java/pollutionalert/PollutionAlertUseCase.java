package pollutionalert;

import datasources.userpreferences.IUserPreferencesRepository;

public class PollutionAlertUseCase {

    private IUserPreferencesRepository mUserPreferencesRepository;

    public PollutionAlertUseCase(IUserPreferencesRepository preferencesRepository) {
        this.mUserPreferencesRepository = preferencesRepository;
    }

    public boolean arePollutionAlertsEnabled() {
        return mUserPreferencesRepository.arePollutionAlertsEnabled();
    }

    public void setPollutionAlertsEnabled() {
        mUserPreferencesRepository.setPollutionAlertsEnabled();
    }

    public void setPollutionAlertsDisabled() {
        mUserPreferencesRepository.setPollutionAlertsDisabled();
    }

    public int getPollutionScenarioLevel() {
        return mUserPreferencesRepository.getPollutionAlertScenarioLevel();
    }

    public String getPollutionAlertDate() {
        return mUserPreferencesRepository.getPollutionAlertDate();
    }

}
