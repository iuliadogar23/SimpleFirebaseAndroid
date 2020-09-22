package com.example.simplefirebaseandroid.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.simplefirebaseandroid.ui.welcome.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController {

    private LoginService loginService;

    public LoginController() {
        loginService = LoginService.getInstance();
    }

    public void loginAccount(final Context context, FirebaseAuth firebaseAuth, String email, String password, ProgressBar progressBar) {
        String fieldsAreValidResponse = loginService.fieldsAreValid(email, password);
        if (fieldsAreValidResponse.equals("ok")) {
            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                progressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Intent welcome = new Intent(context, WelcomeActivity.class);
                    context.startActivity(welcome);
                } else {
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else
            Toast.makeText(context, fieldsAreValidResponse, Toast.LENGTH_LONG).show();

    }

}
