package dependencies;

import repository.IPdfRepository;
import repository.IPollutantsRepository;
import repository.IStationsRepository;
import repository.IUserPreferencesRepository;

public interface IAppInjector {

    IPdfRepository getPdfRepository();

    IStationsRepository getStationsRepository();

    IPollutantsRepository getPollutantsRepository();

    IUserPreferencesRepository getUserPreferencesRepository();
}
