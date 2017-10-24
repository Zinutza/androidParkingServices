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
import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.RegistrationService;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.registrationService;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button registerButton;

    TextView emailError;
    TextView passwordError;
    TextView confirmPasswordError;
    TextView passwordMatchError;
    TextView serverError;

    RegistrationService registrationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        emailError = (TextView) findViewById(R.id.emailError);
        passwordError = (TextView) findViewById(R.id.passwordError);
        confirmPasswordError = (TextView) findViewById(R.id.confirmPasswordError);
        passwordMatchError = (TextView) findViewById(R.id.passwordMatchError);
        serverError = (TextView) findViewById(R.id.serverError);
        registrationService = registrationService();
    }

    @Override
    public void onClick(View view) {
        Log.i("RegisterActivity", "Sending registration request");
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmedPassword = etConfirmPassword.getText().toString();

        clearErrors();

        if(valid(email, password, confirmedPassword)) {
            RegistrationDetails registrationDetails = new RegistrationDetails(email, password);
            Response<User> response = registrationService.register(registrationDetails);
            if(!response.isEmpty()) {
                transitionToMapActivity(response.getEntity());
            } else {
                String errorText = response.getError();
                if(errorText.contains("USER_EXISTS")) {
                    serverError.setText("User already exists");
                    serverError.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void clearErrors() {
        serverError.setVisibility(View.INVISIBLE);
        emailError.setVisibility(View.INVISIBLE);
        passwordError.setVisibility(View.INVISIBLE);
        confirmPasswordError.setVisibility(View.INVISIBLE);
        passwordMatchError.setVisibility(View.INVISIBLE);
    }

    private boolean shouldDisplayPasswordMatchError(String password, String confirmedPassword) {
        return !confirmedPassword.isEmpty() && !password.isEmpty() && !password.equals(confirmedPassword);
    }

    private boolean valid(String email, String password, String confirmedPassword) {

        boolean valid = true;

        if(email.isEmpty()) {
            emailError.setVisibility(View.VISIBLE);
            valid = false;
        }

        if(password.isEmpty()) {
            passwordError.setVisibility(View.VISIBLE);
            valid =  false;
        }

        if(confirmedPassword.isEmpty()) {
            confirmPasswordError.setVisibility(View.VISIBLE);
            valid =  false;
        }

        if(shouldDisplayPasswordMatchError(password, confirmedPassword)) {
            passwordMatchError.setVisibility(View.VISIBLE);
            valid =  false;
        }

        return valid;
    }

    private void transitionToMapActivity(User user) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
