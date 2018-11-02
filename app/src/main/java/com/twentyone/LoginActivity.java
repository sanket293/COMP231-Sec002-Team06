package com.twentyone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private EditText etEmail, etPassword;
    private DataBaseHelper dataBaseHelper;
    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findId();
    }


    private void findId() {
        dataBaseHelper = DataBaseHelper.getInstance(context); //  create instance of db

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void onLoginbtnClick(View view) {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (email.equalsIgnoreCase("")) {

            Toast.makeText(context, getResources().getString(R.string.err_enter_your_email), Toast.LENGTH_SHORT).show();

            return;
        }
        if (password.equalsIgnoreCase("")) {
            Toast.makeText(context, getResources().getString(R.string.err_enter_your_password), Toast.LENGTH_SHORT).show();
            return;
        }


        UserFields user = dataBaseHelper.checkCredentials(email, password);
        if (user != null) {


            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

        } else {


            Toast.makeText(context, getResources().getString(R.string.err_credentialisnotmatching), Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelbtnClick(View view) {


        etEmail.setText("");
        etPassword.setText("");

    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        finish();
    }

}
