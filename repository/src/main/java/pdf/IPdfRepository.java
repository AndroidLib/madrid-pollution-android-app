package pdf;

import java.io.File;

public interface IPdfRepository {


    boolean isDirectivePdfAvailableInDevice();

    boolean isBoePdfAvailableInDevice();

    void copyBoeToDevice();

    void copyDirectiveToDevice();

    File getBoeFile();

    File getDirectiveFile();

}
