package com.example.alpin.sharedpreferences;

/**
 * Created by alpin on 17/07/17.
 */

public class Person {
    private String name, email, password;
    private int noTelp;

    public Person(String name, String email, String password, int noTelp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.noTelp = noTelp;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getNoTelp() {
        return noTelp;
    }

    public String info(){
        return "Nama : "+name+"\n email : "+email+"\n alamat : "+password+"\n No Telp: "+noTelp;
    }
}
