package regulations;

public class RegulationsPresenter {

    private IRegulationsView mView;
    private RegulationsUseCase mRegulationsUseCase;

    public RegulationsPresenter(IRegulationsView view) {
        this.mView = view;
        mRegulationsUseCase = new RegulationsUseCase(mView.getAppInjector().getPdfRepository());
    }

    public void onResume() {

        if (mRegulationsUseCase.isDirectiveAvailable()) {
            mView.showDirectiveRead();
        } else {
            mView.showDirectiveDownload();
        }

        if (mRegulationsUseCase.isBoeAvailable()) {
            mView.showBoeRead();
        } else {
            mView.showBoeDownload();
        }

    }


    public void onDirectivePressed() {

        if (mRegulationsUseCase.isDirectiveAvailable()) {
            mView.showPdfFile(mRegulationsUseCase.getDirective());
        } else {
            mRegulationsUseCase.downloadDirective();
            mView.showDirectiveRead();
        }
    }


    public void onBoePressed() {

        if (mRegulationsUseCase.isBoeAvailable()) {
            mView.showPdfFile(mRegulationsUseCase.getBoe());
        } else {
            mRegulationsUseCase.downloadBoe();
            mView.showBoeRead();
        }
    }

}
