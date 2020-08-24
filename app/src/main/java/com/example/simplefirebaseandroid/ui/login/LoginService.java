package com.example.simplefirebaseandroid.ui.login;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

public class LoginService extends ViewModel {

    private static LoginService single_instance = null;

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (single_instance == null)
            single_instance = new LoginService();
        return single_instance;
    }


    private boolean isEmailValid(String email) {
        if (!isFieldInitialized(email)) {
            return false;
        }
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isPasswordValid(String password) {
        if (!isFieldInitialized(password))
            return false;
        if (password.length()<6)
            return false;
        return true;
    }

    private boolean isFieldInitialized(String field)
    {
        if (field == null || field == "")
            return false;
        return true;
    }

    public String fieldsAreValid(String email, String password) {
        if (!isPasswordValid(password))
            return "Password is empty or has less than 6 characters!";
        if (!isEmailValid(email))
            return "Email does not have the correct format!";
        return "ok";
    }

}
