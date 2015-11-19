package mainlistmenu;

public class MainListMenuPresenter {

    private IMainListMenuView mView;


    public MainListMenuPresenter(IMainListMenuView view){
        this.mView = view;
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


}
