package mainlistmenu;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainListMenuPresenterTest {

    //Class under test
    MainListMenuPresenter presenter;

    //Mocks
    IMainListMenuView mockMainListMenuView;

    @Before
    public void setUp() throws Exception {

        mockMainListMenuView = mock(IMainListMenuView.class);

        presenter = new MainListMenuPresenter(mockMainListMenuView);

    }

    @Test
    public void testOnMadridCityMapSelected() throws Exception {

        presenter.onMadridCityMapPressed();
    }

    @Test
    public void testOnMadridRegionMapSelected() throws Exception {

        presenter.onMadridRegionMapPressed();
    }

    @Test
    public void testOnRegulationsSelected() throws Exception {

        presenter.onRegulationsPressed();
    }

    @Test
    public void testOnPollutantsSelected() throws Exception {

        presenter.onPollutantsPressed();
        verify(mockMainListMenuView).proceedToPollutantsView();
    }
}