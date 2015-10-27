package mainlistmenu;

public class MainListMenuPresenter {

    private IMainListMenuView mView;


    public MainListMenuPresenter(IMainListMenuView view){
        this.mView = view;
    }

    public void onMadridCityMapPressed(){

    }

    public void onMadridRegionMapPressed(){

    }

    public void onRegulationsPressed(){

    }

    public void onPollutantsPressed(){

        mView.proceedToPollutantsView();
    }


}
