package regulations;

import java.io.File;

import baseview.IBaseView;

public interface IRegulationsView extends IBaseView {

    void showBoeDownload();

    void showBoeRead();

    void showDirectiveDownload();

    void showDirectiveRead();

    void showPdfFile(File pdfFile);

}
