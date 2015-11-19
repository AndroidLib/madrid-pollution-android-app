package com.greenlionsoft.pollution.madrid.dependencies;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import repository.IPdfRepository;

public class PdfRepository implements IPdfRepository {

    private static final String DIRECTIVE_PDF = "DIRECTIVA_2008_50_CE.pdf";
    private static final String BOE_PDF = "BOE_A_2011_1645.pdf";

    private Context mContext = AppInjector.getInstance().getApplicationContext();

    public PdfRepository() {

    }

    @Override
    public boolean isDirectivePdfAvailableInDevice() {
        return checkIfFileExistsInPublicFolder(DIRECTIVE_PDF);
    }

    @Override
    public boolean isBoePdfAvailableInDevice() {
        return checkIfFileExistsInPublicFolder(BOE_PDF);
    }

    @Override
    public void copyBoeToDevice() {
        copyFileFromAssetsToPublicFolder(BOE_PDF);
    }

    @Override
    public void copyDirectiveToDevice() {
        copyFileFromAssetsToPublicFolder(DIRECTIVE_PDF);
    }

    @Override
    public File getBoeFile() {
        return new File(mContext.getExternalFilesDir(null), BOE_PDF);
    }

    @Override
    public File getDirectiveFile() {
        return new File(mContext.getExternalFilesDir(null), DIRECTIVE_PDF);
    }


    private boolean checkIfFileExistsInPublicFolder(String fileName) {

        File file = new File(mContext.getExternalFilesDir(null), fileName);

        return file.exists();
    }

    private void copyFileFromAssetsToPublicFolder(String fileName) {

        AssetManager assetManager = mContext.getAssets();

        InputStream in;
        OutputStream out;

        File file = new File(mContext.getExternalFilesDir(null), fileName);

        try {
            in = assetManager.open(fileName);
            out = new FileOutputStream(file);
            byte[] data = new byte[in.available()];
            in.read(data);
            out.write(data);
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
