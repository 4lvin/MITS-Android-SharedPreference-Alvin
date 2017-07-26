package com.example.alpin.sharedpreferences.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alpin.sharedpreferences.MainActivity;
import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.Person;
import com.example.alpin.sharedpreferences.utility.DatabaseHandler;
import com.example.alpin.sharedpreferences.utility.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass;
    private TextView tvResult;
    private final String TAG = LoginActivity.class.getSimpleName();

    private SessionManager sessionManager;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = SessionManager.getInstance();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_pass);
        db = DatabaseHandler.getInstance();
        for (Person person : db.getAllUser()) {
            Log.d(TAG, "data : " + person.toString());

        }


    }

    public void login(View view) {


        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("text can't be empty");
            etEmail.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            etPass.setError("please insert your password !");
            etPass.requestFocus();
            return;
        }

        if (db.checkUser(email, pass)) {
            sessionManager.setLogin(email, pass);
            Intent data = new Intent(this, MainActivity.class);
            startActivity(data);
            finish();
        } else Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
    }


/*           {

        }*/


public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
        }

        }
