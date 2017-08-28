package com.example.themsteam.itweekdemo.ui.presenter;

import com.example.themsteam.itweekdemo.data.IDatabase;
import com.example.themsteam.itweekdemo.data.QRCodeModel;
import com.example.themsteam.itweekdemo.ui.IErrorBundle;
import com.example.themsteam.itweekdemo.ui.IQRCodeListView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vshabanov on 15-Aug-17.
 */

public class QRCodeListPresenter implements IListPresenter {
    private final IQRCodeListView view;
    private final IDatabase database;
    private final IErrorBundle errorBundle;


    public QRCodeListPresenter(IQRCodeListView view, IDatabase database, IErrorBundle errorBundle) {
        this.view = view;
        this.database = database;
        this.errorBundle = errorBundle;
    }

    public List<QRCodeModel> getQRCodes() {
        return database.getScannedQRCodes();
    }

    public boolean isAllItemsScanned(List<QRCodeModel> currentProgress) {
        Set<QRCodeModel> currentProgressSet = new HashSet<>(currentProgress);
        Set<QRCodeModel> allQRCodesSet = new HashSet<>(database.getHardcodedQRCodes());

        return currentProgressSet.equals(allQRCodesSet);
    }

    public void proceedNewQRCode(String code) {
        try {
            database.addQRCode(code);
            List<QRCodeModel> qrCodes = database.getScannedQRCodes();
            if (!isAllItemsScanned(qrCodes)) {
                view.showScanSuccess(qrCodes);
            }
        } catch (Exception e) {
            view.showScanError(errorBundle.toString(e));
        }
    }

    @Override
    public void shareLink() {
        view.shareLink(database.getAchievementLink());
    }
}
