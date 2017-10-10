package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zina.parkingandroidapp.model.LoginDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.LoginService;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.loginService;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button loginButton;
    Button registerButton;

    TextView emailError;
    TextView passwordError;

    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton); // registration button
        emailError = (TextView) findViewById(R.id.emailError);
        passwordError = (TextView) findViewById(R.id.passwordError);

        loginService = loginService();
        loginButton.setOnClickListener(new LoginButtonOnClickListener());

        //add a handler and a listener for register button
        View.OnClickListener registerButtonHandler = new View.OnClickListener() {
            public void onClick(View v) {
                transitionToRegisterActivity();
            }
        };
        registerButton.setOnClickListener(registerButtonHandler);
    }

    private void transitionToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void transitionToMapActivity(User user) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private class LoginButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if(valid(email, password)) {
                LoginDetails loginDetails = new LoginDetails(email, password);
                User user = loginService.login(loginDetails);
                if(user != null) {
                    transitionToMapActivity(user);
                    Log.i("Login", "logged in successfully " + user.getId());
                }
            }
        }
    }

    private boolean valid(String email, String password) {
        if(!email.isEmpty() && !password.isEmpty()) {
            return true;
        }

        if(email.isEmpty()) {
            emailError.setVisibility(View.VISIBLE);
        } else {
            emailError.setVisibility(View.INVISIBLE);
        }

        if(password.isEmpty()) {
            passwordError.setVisibility(View.VISIBLE);
        }else {
            passwordError.setVisibility(View.INVISIBLE);
        }
        return false;
    }
}
