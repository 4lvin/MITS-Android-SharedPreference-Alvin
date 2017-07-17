package com.example.alpin.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText nama,email,alamat,notelp;
    public static String KEY_NAME = "name";
    public static String KEY_EMAIL = "email";
    public static String KEY_ALAMAT = "alamat";
    public static String KEY_NO_TELP = "notelp";
    public static String PREFERENCE_NAME = "preference";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_WORLD_READABLE);

        nama = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        alamat = (EditText) findViewById(R.id.et_alamat);
        notelp = (EditText) findViewById(R.id.et_noTelp);



    }
    public void submit(View view){
        Person person = new Person(nama.getText().toString(), email.getText().toString(), alamat.getText().toString(),
                Integer.valueOf(notelp.getText().toString()));
        setPerson(person);
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    private void setPerson(Person person){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, person.getName());
        editor.putString(KEY_EMAIL, person.getEmail());
        editor.putString(KEY_ALAMAT, person.getAlamat());
        editor.putInt(KEY_NO_TELP, person.getNoTelp());
        editor.commit();
    }


    private Person getPerson(){
        Person person = new Person(sharedPreferences.getString(KEY_NAME,""), sharedPreferences.getString(KEY_EMAIL,""),
                sharedPreferences.getString(KEY_ALAMAT,""),sharedPreferences.getInt(KEY_NO_TELP,0));
        return person;
    }
    }

