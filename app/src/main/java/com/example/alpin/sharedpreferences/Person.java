package com.example.alpin.sharedpreferences;

/**
 * Created by alpin on 17/07/17.
 */

public class Person {
    private String name, email, alamat;
    private int noTelp;

    public Person(String name, String email, String alamat, int noTelp) {
        this.name = name;
        this.email = email;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getNoTelp() {
        return noTelp;
    }
    public String info(){
        return "Nama : "+name+"email : "+email+"alamat : "+alamat+"No Telp: "+noTelp;
    }
}
