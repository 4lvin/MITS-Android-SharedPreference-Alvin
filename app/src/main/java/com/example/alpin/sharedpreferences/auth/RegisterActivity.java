package com.example.alpin.sharedpreferences.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.Person;
import com.example.alpin.sharedpreferences.utility.DatabaseHandler;
import com.example.alpin.sharedpreferences.utility.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText nama, email, alamat, notelp;
    private final String TAG = RegisterActivity.class.getSimpleName();

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

    public void submit(View view) {
        DatabaseHandler db = DatabaseHandler.getInstance();

        String etNama, etEmail, etAlamat;
        etNama = nama.getText().toString();
        etEmail = email.getText().toString();
        etAlamat = alamat.getText().toString();

        if (etNama.isEmpty()) {
            nama.setError("Please insert name !");
            nama.requestFocus();
            return;
        }
        if (etEmail.isEmpty()) {
            email.setError("Please insert email !");
            email.requestFocus();
            return;
        }
        if (etAlamat.isEmpty()) {
            alamat.setError("Please insert Address !");
            alamat.requestFocus();
            return;
        }
        Person per = new Person(nama.getText().toString(), email.getText().toString(), alamat.getText().toString(),
                Integer.valueOf(notelp.getText().toString()));

        db.addUser(per);
        SessionManager.getInstance().setPerson(per);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        for (Person person : db.getAllUser()) {
            Log.d(TAG, "data : " + person.toString());

        }
    }


}


