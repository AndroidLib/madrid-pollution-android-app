package regulations;

import java.io.File;

import datasources.pdf.IPdfRepository;

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

    public boolean isNo2RegulationAvailable() {
        return mPdfRepository.isNo2RegulationAvailableInDevice();
    }


    public File getBoe() {
        return mPdfRepository.getBoeFile();
    }

    public File getDirective() {
        return mPdfRepository.getDirectiveFile();
    }

    public File getNo2Regulation() {
        return mPdfRepository.getNo2RegulationFile();
    }

    public void downloadDirective() {
        mPdfRepository.copyDirectiveToDevice();
    }

    public void downloadBoe() {
        mPdfRepository.copyBoeToDevice();
    }

    public void downloadNo2Regulation() {
        mPdfRepository.copyNo2RegulationToDevice();
    }


}
