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

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        Button login = findViewById(R.id.loginButton);
        Button loginGoogle = findViewById(R.id.customGoogleButton);
        Button signUp = findViewById(R.id.signUpButton);

    }


    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void signUp(View view)
    {

        Intent signUpIntent = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(signUpIntent);

    }
}
