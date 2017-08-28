package com.example.themsteam.itweekdemo.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.themsteam.itweekdemo.R;
import com.example.themsteam.itweekdemo.data.Exception.QRCodeDoesNotMatchException;
import com.example.themsteam.itweekdemo.data.Exception.QRCodeDuplicateException;
import com.example.themsteam.itweekdemo.ui.ITWApp;
import com.example.themsteam.itweekdemo.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vladimir on 18.08.2017.
 */

public class QRCodeDatabase implements IDatabase {
    private List<QRCodeModel> qrCodeModelList;

    private static QRCodeDatabase database = null;

    private final String PREFERENCES_KEY = "preferences_key";
    private final String CODES_ARRAY_KEY = "codes_array_key";
    private final String DEFAULT_VALUE = "";

    private Context context;

    public static QRCodeDatabase getInstance() {
        if (database == null) {
            database = new QRCodeDatabase(ITWApp.getAppContext());
        }
        return database;
    }


    private QRCodeDatabase(Context context) {
        this.context = context;
        qrCodeModelList = JSONParser.getQRCodeList(context);
    }

    public List<QRCodeModel> getScannedQRCodes() {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        String codesArray = preferences.getString(CODES_ARRAY_KEY, DEFAULT_VALUE);
        List<QRCodeModel> list = new ArrayList<>();

        if (!codesArray.equals("")) {
            String[] ids = codesArray.split(" ");

            for (int i = 0; i < ids.length; i++) {
                for (QRCodeModel model : qrCodeModelList) {
                    if (Integer.parseInt(ids[i]) == model.getId()) {
                        list.add(model);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<QRCodeModel> getHardcodedQRCodes() {
        return qrCodeModelList;
    }

    public void addQRCode(String qrCode) throws QRCodeDuplicateException, QRCodeDoesNotMatchException {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        String codesArray = preferences.getString(CODES_ARRAY_KEY, DEFAULT_VALUE);
        List<String> ids = Arrays.asList(codesArray.split(" "));

        for (QRCodeModel model : qrCodeModelList) {
            if (qrCode.equals(String.valueOf(model.getId()))) {
                if (!ids.contains(String.valueOf(model.getId()))) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(CODES_ARRAY_KEY, StringUtils.append(codesArray, String.valueOf(model.getId())));
                    editor.apply();
                    return;
                } else {
                    throw new QRCodeDuplicateException();
                }
            }
        }
        throw new QRCodeDoesNotMatchException();
    }

    @Override
    public String getAchievementLink() {
        return ITWApp.getAppContext().getResources().getString(R.string.achievement_link);
    }
}
