package com.example.themsteam.itweekdemo.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by bober2 on 18/08/2017.
 */

public class ITWApp extends Application {
    private static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getAppContext() {
        return sContext;
    }
}
