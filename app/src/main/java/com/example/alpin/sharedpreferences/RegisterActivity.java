package com.example.alpin.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText nama,email,alamat,notelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        nama = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        alamat = (EditText) findViewById(R.id.et_alamat);
        notelp = (EditText) findViewById(R.id.et_noTelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    public void submit(View view){
        Person per = new Person(nama.getText().toString(), email.getText().toString(), alamat.getText().toString(),
                Integer.valueOf(notelp.getText().toString()));
        if(per !=null) {
            SessionManager.getInstance().setPerson(per);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
    }


    }
    }

