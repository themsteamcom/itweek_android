package com.example.themsteam.itweekdemo.ui;

import android.content.Context;

import com.example.themsteam.itweekdemo.R;
import com.example.themsteam.itweekdemo.data.Exception.QRCodeDoesNotMatchException;
import com.example.themsteam.itweekdemo.data.Exception.QRCodeDuplicateException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bober2 on 18/08/2017.
 */

public class ErrorBundle implements IErrorBundle {
    private final Context context;

    public ErrorBundle(Context context) {
        this.context = context;
    }

    @Override
    public String toString(Exception e) {
        Map<Class<? extends Exception>, String> errors = new HashMap<>();

        errors.put(QRCodeDuplicateException.class, context.getString(R.string.scan_duplicate_error));
        errors.put(QRCodeDoesNotMatchException.class, context.getString(R.string.scan_no_match_error));

        for (Map.Entry<Class<? extends Exception>, String> entry : errors.entrySet()) {
            if (entry.getKey().isInstance(e)) {
                return entry.getValue();
            }
        }

        return context.getString(R.string.undefined_error);
    }
}
