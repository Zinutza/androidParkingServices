package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.LoginService;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.*;

public class LoginActivity extends AppCompatActivity {

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
        // registration button

        loginService = loginService();

        View.OnClickListener loginButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                LoginDetails loginDetails = new LoginDetails(email, password);
                User user = loginService.login(loginDetails);

                transitionToMapActivity(user);

                Log.i("Login", "logged in successfully " + user.getId());
            }
        };
        loginButton.setOnClickListener(loginButtonHandler);

        //add a handler and a listener for register button
    }

    private void transitionToMapActivity(User user) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
