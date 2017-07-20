package com.example.alpin.sharedpreferences;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tv_result);



    }

    public void submit(View view){
        SessionManager.getInstance().clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void doSubmit(View view){
        AlertDialog.Builder mydialogbuilder = new AlertDialog.Builder(MainActivity.this);
        mydialogbuilder
                .setTitle("DATA")
                .setMessage(SessionManager.getInstance().getPerson().info())
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }

                });

        AlertDialog mydialog = mydialogbuilder.create();
        mydialog.show();
    }
}
