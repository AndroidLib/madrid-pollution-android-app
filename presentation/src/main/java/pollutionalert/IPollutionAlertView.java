package pollutionalert;

import baseview.IBaseView;

public interface IPollutionAlertView extends IBaseView {

    void showPollutionAlertsEnabled();

    void showPollutionAlertsDisabled();

    void showScenario1Alert();

    void showScenario2Alert();

    void showScenario3Alert();

    void showScenario4Alert();

    void showScenarioTitle(int scenarioLevel, String yyyyMMdd_HHmmss);

    void showNoScenarioTitle();

}
