package com.example.simplefirebaseandroid.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.startActivity(mainIntent);
            WelcomeActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);

    }
}
