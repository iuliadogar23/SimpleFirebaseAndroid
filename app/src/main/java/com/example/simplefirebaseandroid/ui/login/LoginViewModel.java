package com.example.simplefirebaseandroid.ui.login;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {


    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null;
    }
}
