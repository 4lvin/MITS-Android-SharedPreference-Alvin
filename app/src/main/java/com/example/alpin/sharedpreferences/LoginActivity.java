package com.example.alpin.sharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass;
    private TextView tvResult;


    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = SessionManager.getInstance();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPass = (EditText) findViewById(R.id.et_pass);

    }

    public void login(View view){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(email.isEmpty()){
            etEmail.setError("text can't be empty");
            etEmail.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            etPass.setError("please insert your password !");
            etPass.requestFocus();
            return;
        }

        if(etEmail.getText().toString().equalsIgnoreCase(sessionManager.getPerson().getEmail())
                && etPass.getText().toString().equalsIgnoreCase(sessionManager.getPerson().getPassword())
                ){

            sessionManager.setLogin(true);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    public void register(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
