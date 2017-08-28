package com.example.themsteam.itweekdemo.data;

import com.example.themsteam.itweekdemo.data.Exception.QRCodeDoesNotMatchException;
import com.example.themsteam.itweekdemo.data.Exception.QRCodeDuplicateException;

import java.util.List;

/**
 * Created by vshabanov on 16-Aug-17.
 */

public interface IDatabase {
    List<QRCodeModel> getScannedQRCodes();
    List<QRCodeModel> getHardcodedQRCodes();
    void addQRCode(String qrCode) throws QRCodeDuplicateException, QRCodeDoesNotMatchException;
    String getAchievementLink();
}
