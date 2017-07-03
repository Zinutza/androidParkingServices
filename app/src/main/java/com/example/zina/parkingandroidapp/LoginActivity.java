package com.example.zina.parkingandroidapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.LoginService;
import com.example.zina.parkingandroidapp.services.RegistrationService;
import com.example.zina.parkingandroidapp.util.ApplicationContext;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    EditText etPassword;
    Button loginButton;

    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        loginService = loginService();
    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        LoginDetails loginDetails = new LoginDetails(email, password);
        User user = loginService.login(loginDetails);
    }
}
