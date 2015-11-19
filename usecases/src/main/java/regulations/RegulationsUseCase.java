package regulations;

import java.io.File;

import repository.IPdfRepository;

public class RegulationsUseCase {


    private IPdfRepository mPdfRepository;

    public RegulationsUseCase(IPdfRepository pdfRepository) {
        this.mPdfRepository = pdfRepository;
    }


    public boolean isDirectiveAvailable() {
        return mPdfRepository.isDirectivePdfAvailableInDevice();
    }


    public boolean isBoeAvailable() {
        return mPdfRepository.isBoePdfAvailableInDevice();
    }


    public File getBoe() {
        return mPdfRepository.getBoeFile();
    }

    public File getDirective() {
        return mPdfRepository.getDirectiveFile();
    }

    public void downloadDirective() {
        mPdfRepository.copyDirectiveToDevice();
    }

    public void downloadBoe() {
        mPdfRepository.copyBoeToDevice();
    }


}
