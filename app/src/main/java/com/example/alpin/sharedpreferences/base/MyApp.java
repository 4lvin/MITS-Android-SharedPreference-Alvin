package com.example.alpin.sharedpreferences.base;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.alpin.sharedpreferences.model.User;
import com.example.alpin.sharedpreferences.utility.SessionManager;


/**
 * Created by alpin on 20/07/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
        //DatabaseHandler.init(this);
        Configuration.Builder confiBuilder = new Configuration.Builder(this);
        confiBuilder.addModelClasses(User.class);
        ActiveAndroid.initialize(this);
    }
}
