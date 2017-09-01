package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zina.parkingandroidapp.model.RegistrationDetails;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.RegistrationService;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.registrationService;

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
        Log.i("RegisterActivity", "Sending registration request");
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmedPassword = etConfirmPassword.getText().toString();

        //TODO check passwords match

        RegistrationDetails registrationDetails = new RegistrationDetails(email, password);
        User user = registrationService.register(registrationDetails);
        if(user != null) {
            transitionToMapActivity(user);
        }
        //TODO: handle error correctly
    }

    private void transitionToMapActivity(User user) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
