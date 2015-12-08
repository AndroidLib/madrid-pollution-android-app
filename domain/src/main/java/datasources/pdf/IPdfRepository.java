package datasources.pdf;

import java.io.File;

public interface IPdfRepository {


    boolean isDirectivePdfAvailableInDevice();

    boolean isBoePdfAvailableInDevice();

    boolean isNo2RegulationAvailableInDevice();

    void copyBoeToDevice();

    void copyDirectiveToDevice();

    void copyNo2RegulationToDevice();

    File getBoeFile();

    File getDirectiveFile();

    File getNo2RegulationFile();
}
