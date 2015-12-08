package regulations;

import java.io.File;

import baseview.IBaseView;

public interface IRegulationsView extends IBaseView {

    void showBoeDownload();

    void showBoeRead();

    void showDirectiveDownload();

    void showDirectiveRead();

    void showNo2RegulationDownload();

    void showNo2RegulationRead();

    void showPdfFile(File pdfFile);

}
