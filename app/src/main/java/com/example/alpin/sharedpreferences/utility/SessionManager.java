package com.example.alpin.sharedpreferences.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alpin.sharedpreferences.model.Person;

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
        editor.commit();
    }

    public Person getPerson(){
        DatabaseHandler db = DatabaseHandler.getInstance();
        Person person = new Person(preferences.getString(KEY_NAME,""),
                preferences.getString(KEY_EMAIL,""),preferences.getString(KEY_ALAMAT,""),
                preferences.getInt(KEY_NO_TELP,0));
        return person;
    }

    public void setLogin(String email, String pass) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ALAMAT, pass);
        editor.putBoolean(ISLOGGEDIN, true);
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
