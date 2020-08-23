package com.example.simplefirebaseandroid.ui.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplefirebaseandroid.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private SignUpController signUpController;

    private EditText usernameSignUp;
    private EditText passwordSignUp;
    private EditText userPhoneNumber;
    private EditText userEmail;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        usernameSignUp = findViewById(R.id.usernameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
        userEmail = findViewById(R.id.userEmail);

        mAuth = FirebaseAuth.getInstance();
        signUpController = new SignUpController();

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if(mAuth.getCurrentUser() != null)
        {
            Toast.makeText(getApplicationContext(), "Already logged in!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createAccount(View view) {

        signUpController.createAccount(getApplicationContext(), mAuth, usernameSignUp.getText().toString(), passwordSignUp.getText().toString(), userPhoneNumber.getText().toString(), userEmail.getText().toString());

    }

}
