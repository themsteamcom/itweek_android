package com.example.themsteam.itweekdemo.ui;

import com.example.themsteam.itweekdemo.data.QRCodeModel;

import java.util.List;

/**
 * Created by vshabanov on 16-Aug-17.
 */

public interface IQRCodeListView {
    void showScanSuccess(List<QRCodeModel> qrCodes);
    void showScanCompleted(List<QRCodeModel> qrCodes);
    void showScanError(String errorMessage);
    void shareLink(String link);
}
