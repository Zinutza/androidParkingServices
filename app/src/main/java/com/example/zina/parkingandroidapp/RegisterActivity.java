package com.example.zina.parkingandroidapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.RegistrationService;
import com.example.zina.parkingandroidapp.util.ApplicationContext;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.*;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button registerButton;

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

        registrationService = registrationService();
    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmedPassword = etConfirmPassword.getText().toString();
        RegistrationDetails registrationDetails = new RegistrationDetails(email, password, confirmedPassword);
        User user = registrationService.register(registrationDetails);
    }
}
