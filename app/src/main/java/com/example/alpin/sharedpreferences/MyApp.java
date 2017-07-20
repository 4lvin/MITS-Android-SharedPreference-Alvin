package com.example.alpin.sharedpreferences;

import android.app.Application;


/**
 * Created by alpin on 20/07/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
    }
}
