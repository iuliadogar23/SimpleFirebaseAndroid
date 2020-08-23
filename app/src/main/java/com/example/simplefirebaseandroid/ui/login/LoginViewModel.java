package com.example.simplefirebaseandroid.ui.login;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {


    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    private boolean isEmailExistent(String email)
    {
        return true;
    }

    private boolean isPasswordValid(String password) {
        return password != null;
    }
}
