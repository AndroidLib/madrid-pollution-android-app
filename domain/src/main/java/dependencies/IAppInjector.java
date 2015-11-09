package dependencies;

import pdf.IPdfRepository;

public interface IAppInjector {

    IPdfRepository getPdfRepository();
}
