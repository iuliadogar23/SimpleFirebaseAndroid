package com.example.simplefirebaseandroid.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.ui.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private LoginViewModel loginViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button login;
    private Button loginGoogle;
    private Button signUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        loginGoogle = findViewById(R.id.customGoogleButton);
        signUp = findViewById(R.id.signUpButton);

    }


    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void signUp(View view)
    {

        Intent signUpIntent = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(signUpIntent);

    }

    @Override
    public void onClick(View v) {

    }
}
