package com.example.simplefirebaseandroid.ui.signup;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

public class SignUpService extends ViewModel {

    private static SignUpService single_instance = null;

    private SignUpService() {
    }

    public static SignUpService getInstance() {
        if (single_instance == null)
            single_instance = new SignUpService();
        return single_instance;
    }

    private boolean isEmailValid(String email) {
        if (!isFieldInitialized(email)) {
            return false;
        }
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        if (!isFieldInitialized(phoneNumber))
            return false;
        return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
    }

    private boolean isFieldValid(String field) {
        if (!isFieldInitialized(field))
            return false;
        return true;
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

    public String fieldsAreValid(String username, String password, String phoneNumber, String email) {
        if (!isFieldValid(username))
            return "Username is empty!";
        if (!isPasswordValid(password))
            return "Password is empty or has less than 6 characters!";
        if (!isPhoneNumberValid(phoneNumber))
            return "Phone number does not have the correct format!";
        if (!isEmailValid(email))
            return "Email does not have the correct format!";
        return "ok";
    }

}
