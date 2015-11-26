package dependencies;

import datasources.pdf.IPdfRepository;
import datasources.pollutants.IPollutantsRepository;
import datasources.stations.IStationsRepository;
import datasources.userpreferences.IUserPreferencesRepository;
import permissions.IPermissionsRepository;

public interface IAppInjector {

    IPdfRepository getPdfRepository();

    IStationsRepository getStationsRepository();

    IPollutantsRepository getPollutantsRepository();

    IUserPreferencesRepository getUserPreferencesRepository();

    IPermissionsRepository getPermissionsRepository();
}
