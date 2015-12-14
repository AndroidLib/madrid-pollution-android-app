package pollutionalert;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class PollutionAlertPresenter {

    private IPollutionAlertView mView;
    private PollutionAlertUseCase mPollutionAlertUseCase;


    public PollutionAlertPresenter(IPollutionAlertView view) {
        this.mView = view;

        this.mPollutionAlertUseCase = new PollutionAlertUseCase(
                view.getAppInjector().getUserPreferencesRepository());

        if (mPollutionAlertUseCase.arePollutionAlertsEnabled()) {
            mView.showPollutionAlertsEnabled();
        } else {
            mView.showPollutionAlertsDisabled();
        }

        displayAlertContent();
    }

    public void onPollutionAlertsEnabled() {
        mPollutionAlertUseCase.setPollutionAlertsEnabled();
        mView.showPollutionAlertsEnabled();
    }

    public void onPollutionAlertsDisabled() {
        mPollutionAlertUseCase.setPollutionAlertsDisabled();
        mView.showPollutionAlertsDisabled();
    }

    public void onNewPollutionNotification() {
        displayAlertContent();
    }

    private void displayAlertContent() {

        int scenarioLevel = mPollutionAlertUseCase.getPollutionScenarioLevel();
        String dateString = mPollutionAlertUseCase.getPollutionAlertDate();

        DateTime today = new DateTime();
        DateTime alertDate = DateTimeFormat.forPattern("yyyyMMdd_HHmm").parseDateTime(dateString);

        if (scenarioLevel == 0 || isAlertExpired(today, alertDate)) {
            mView.showNoScenarioTitle();
        } else {

            mView.showScenarioTitle(scenarioLevel, dateString);

            switch (scenarioLevel) {
                case 1:
                    mView.showScenario1Alert();
                    break;

                case 2:
                    mView.showScenario2Alert();
                    break;

                case 3:
                    mView.showScenario3Alert();
                    break;

                case 4:
                    mView.showScenario4Alert();
                    break;
            }
        }
    }

    private boolean isAlertExpired(DateTime today, DateTime alertDate) {

        DateTime todayDt = new DateTime(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), 0, 0);
        DateTime alertDt = new DateTime(alertDate.getYear(), alertDate.getMonthOfYear(), alertDate.getDayOfMonth(), 0, 0);

        return todayDt.isAfter(alertDt);

    }
}
