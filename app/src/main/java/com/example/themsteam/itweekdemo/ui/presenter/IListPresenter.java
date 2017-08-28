package com.example.themsteam.itweekdemo.ui.presenter;

import com.example.themsteam.itweekdemo.data.QRCodeModel;

import java.util.List;

/**
 * Created by vshabanov on 16-Aug-17.
 */

public interface IListPresenter {
    List<QRCodeModel> getQRCodes();
    void proceedNewQRCode(String code);
    boolean isAllItemsScanned(List<QRCodeModel> currentProgress);
    void shareLink();
}
