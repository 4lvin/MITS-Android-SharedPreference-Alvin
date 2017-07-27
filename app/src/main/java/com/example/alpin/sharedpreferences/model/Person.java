package com.example.alpin.sharedpreferences.model;

/**
 * Created by alpin on 17/07/17.
 */

public class Person {
    private String name, email, password, noTelp;
    private int id;


    public Person() {
    }

    public Person(String name, String email, String password, String noTelp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.noTelp = noTelp;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", noTelp=" + noTelp +
                ", id=" + id +
                '}';
    }
}
