package com.example.alpin.sharedpreferences.model;

import android.database.Cursor;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;

import java.util.List;

/**
 * Created by alpin on 27/07/17.
 */


@Table(name = "User")
public class User extends Model {
    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Notelp")
    private String noTelp;

    public User() {
        super();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public static List<User> getAll() {
        return new Select().from(User.class)
                .orderBy("Id Desc").execute();
    }

    public static boolean checkUser(String email, String password) {
        User user = new Select()
                .from(User.class)
                .where("Email = '"+ email+"' AND Password = '"+password+"'")
                .executeSingle();
        if (user != null) return true; else return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", noTelp='" + noTelp + '\'' +
                '}';
    }
}
