package com.example.alpin.sharedpreferences.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText nama, email, etPass, notelp;
    private final String TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nama = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_alamat);
        notelp = (EditText) findViewById(R.id.et_noTelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void submit(View view) {

        String etNama, etEmail, etPassword, noTelp;
        etNama = nama.getText().toString();
        etEmail = email.getText().toString();
        etPassword = etPass.getText().toString();
        noTelp = notelp.getText().toString();

        if (etNama.isEmpty()) {
            nama.setError("Please insert name !");
            nama.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail).matches()) {
            email.setError("Please insert email !");
            email.requestFocus();
            return;
        }
        if (etPassword.length() < 8) {
            etPass.setError("Please insert Password !");
            etPass.requestFocus();
            return;
        }
        if (noTelp.isEmpty()) {
            etPass.setError("Please insert No.Telp !");
            etPass.requestFocus();
            return;
        }

        User user = new User();
        user.setName(etNama);
        user.setEmail(etEmail);
        user.setPassword(etPassword);
        user.setNoTelp(noTelp);
        user.save();

        for (User user1 : User.getAll()) {
            Log.d(TAG, "data : " + user1.toString());
        }


       /* Person per = new Person(nama.getText().toString(), email.getText().toString(),
                etPass.getText().toString(), notelp.getText().toString());

        db.addUser(per);
        SessionManager.getInstance().setPerson(per);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        for (Person person : db.getAllUser()) {
            Log.d(TAG, "data : " + person.toString());

        }*/
    }


}


