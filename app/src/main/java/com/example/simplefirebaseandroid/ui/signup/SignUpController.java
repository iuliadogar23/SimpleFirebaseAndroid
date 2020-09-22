package com.example.simplefirebaseandroid.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.simplefirebaseandroid.data.model.User;
import com.example.simplefirebaseandroid.ui.welcome.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpController {

    private static final String TAG = "SignUpActivity";

    private SignUpService signUpService;

    public SignUpController() {
        signUpService = SignUpService.getInstance();
    }

    public void createAccount(final Context context, FirebaseAuth firebaseAuth, final String username, final String password, final String phoneNumber, final String email) {
        String fieldsAreValidResponse = signUpService.fieldsAreValid(username, password, phoneNumber, email);
        if (fieldsAreValidResponse.equals("ok")) {
            firebaseConnection(context, firebaseAuth, username, password, phoneNumber, email);
        } else
            Toast.makeText(context, fieldsAreValidResponse, Toast.LENGTH_LONG).show();


    }

    private void firebaseConnection(final Context context, FirebaseAuth firebaseAuth, final String username, final String password, final String phoneNumber, final String email)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail: success");
                        User user = new User(username, password, phoneNumber, email, null);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(context, "Registration was succesfully made!", Toast.LENGTH_SHORT).show();
                                        Intent welcome = new Intent(context, WelcomeActivity.class);
                                        context.startActivity(welcome);
                                    } else {
                                        Toast.makeText(context, "Oops! Something went wrong...", Toast.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        Log.w(TAG, "createUserWithEmail: failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(context, "This email is already registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }


                });
    }

}
