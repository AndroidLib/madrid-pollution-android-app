package mainlistmenu;

import baseview.IBaseView;

public interface IMainListMenuView extends IBaseView {

    void proceedToMapCityView();

    void proceedToRegulationsView();

    void proceedToPollutantsView();

    void notifyDeviceCompatible();

    void notifyUserFix();

    void notifyDeviceNotCompatible();

}
