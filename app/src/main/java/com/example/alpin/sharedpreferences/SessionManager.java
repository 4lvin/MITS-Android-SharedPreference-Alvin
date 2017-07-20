package com.example.alpin.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alpin on 19/07/17.
 */

public class SessionManager {

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_NO_TELP = "notelp";
    private static final String PREFERENCE_NAME = "preference";
    private static final String ISLOGGEDIN = "isloggedin";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static SessionManager instance;

    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public static void init(Context context){
        instance = new SessionManager(context);
    }

    public synchronized static SessionManager getInstance(){
        return instance;
    }

    public void setPerson(Person person){
        editor.putString(KEY_NAME, person.getName());
        editor.putString(KEY_EMAIL, person.getEmail());
        editor.putString(KEY_ALAMAT, person.getPassword());
        editor.putInt(KEY_NO_TELP, person.getNoTelp());
        setLogin(true);
        editor.commit();
    }

    public Person getPerson(){
        Person person = new Person(preferences.getString(KEY_NAME,""),
                preferences.getString(KEY_EMAIL,""),preferences.getString(KEY_ALAMAT,""),
                preferences.getInt(KEY_NO_TELP,0));
        return person;
    }

    public void setLogin(boolean isLogin){
        editor.putBoolean(ISLOGGEDIN, isLogin);
        editor.commit();
    }

    public  boolean isLogin(){
        return preferences.getBoolean(ISLOGGEDIN,false);
    }


    public void clear(){
        editor.clear();
        editor.commit();
    }
}
