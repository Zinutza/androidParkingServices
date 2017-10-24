package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zina.parkingandroidapp.gateway.util.Response;
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
    TextView serviceError;

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
        serviceError = (TextView) findViewById(R.id.serviceError);

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
            clearErrors();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if(valid(email, password)) {
                LoginDetails loginDetails = new LoginDetails(email, password);
                Response<User> response = loginService.login(loginDetails);
                if(!response.isEmpty()) {
                    transitionToMapActivity(response.getEntity());
                    Log.i("Login", "logged in successfully ");
                } else {
                    handleError(response.getError());
                }
            }
        }

        
    }

    private void clearErrors() {
        serviceError.setVisibility(View.INVISIBLE);
        emailError.setVisibility(View.INVISIBLE);
        passwordError.setVisibility(View.INVISIBLE);
    }

    private void handleError(String error) {
        if(error.equals("\"NO_USER\"")) {
            serviceError.setText("No user found");
            serviceError.setVisibility(View.VISIBLE);
        }
    }

    private boolean valid(String email, String password) {
        if(!email.isEmpty() && !password.isEmpty()) {
            return true;
        }

        if(email.isEmpty()) {
            emailError.setVisibility(View.VISIBLE);
        }

        if(password.isEmpty()) {
            passwordError.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
