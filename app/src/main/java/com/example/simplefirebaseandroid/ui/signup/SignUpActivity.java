package com.example.simplefirebaseandroid.ui.signup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplefirebaseandroid.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
    }
}
