package com.example.alpin.sharedpreferences.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alpin.sharedpreferences.MainActivity;
import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.User;
import com.example.alpin.sharedpreferences.utility.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass;
    private final String TAG = LoginActivity.class.getSimpleName();

    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        if (sessionManager.isLogin()) openDashboard();
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_pass);


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
        User user = new User();
        if (user.checkUser(email, pass)) {
            sessionManager.setLogin(email, pass);
            openDashboard();
        } else Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
    }


public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
        }
    private void openDashboard() {
        Intent data = new Intent(this, MainActivity.class);
        startActivity(data);
        finish();
    }
        }
