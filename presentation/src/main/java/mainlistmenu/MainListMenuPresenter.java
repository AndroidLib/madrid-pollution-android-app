package mainlistmenu;

import cloudmessaging.CloudMessagingUseCase;
import compatibility.CompatibilityUseCase;
import compatibility.ICompatibility;

public class MainListMenuPresenter {

    private IMainListMenuView mView;


    private CloudMessagingUseCase mCloudMessagingUseCase;
    private CompatibilityUseCase mCompatibilityUseCase;

    public MainListMenuPresenter(IMainListMenuView view){
        this.mView = view;
        this.mCloudMessagingUseCase = new CloudMessagingUseCase(view.getAppInjector().getCloudMessaging());
        this.mCompatibilityUseCase = new CompatibilityUseCase(view.getAppInjector().getCompatibility());

        checkDeviceCompatibility();
    }

    public void onMadridCityMapPressed(){
        mView.proceedToMapCityView();
    }

    public void onMadridRegionMapPressed(){

    }

    public void onRegulationsPressed(){
        mView.proceedToRegulationsView();
    }

    public void onPollutantsPressed(){
        mView.proceedToPollutantsView();
    }


    private void checkDeviceCompatibility() {

        int result = mCompatibilityUseCase.isThisDeviceCompatible();

        switch (result) {

            case ICompatibility.COMPATIBILITY_OK:
                //All ok
                mView.notifyDeviceCompatible();
                mCloudMessagingUseCase.register();
                break;

            case ICompatibility.COMPATIBILITY_USER_RESOLVABLE:
                //Try to allow user to fix it
                mView.notifyUserFix();
                break;

            case ICompatibility.COMPATIBILITY_FAILED:
                //Device is not compatible at all :(
                mView.notifyDeviceNotCompatible();
                break;

            default:
                //Shouldn't happen
                mView.notifyDeviceCompatible();
                break;
        }

    }

}
