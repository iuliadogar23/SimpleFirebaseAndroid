package com.example.simplefirebaseandroid.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.ui.signup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button login;
    private Button loginGoogle;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private LoginController loginController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        loginGoogle = findViewById(R.id.customGoogleButton);
        progressBar = findViewById(R.id.loginProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        loginController = new LoginController();

    }

    public void signUp(View view) {

        Intent signUpIntent = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(signUpIntent);

    }

    public void login(View view) {
        loginController.loginAccount(getApplicationContext(), mAuth, usernameEditText.getText().toString(), passwordEditText.getText().toString(), progressBar);
    }

    @Override
    public void onClick(View v) {

    }
}
