package com.example.simplefirebaseandroid.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String username;
    private String displayName;
    private String phoneNumber;
    private String email;
    private String id;

    public User(String username, String displayName, String phoneNumber, String email, String id) {
        this.username = username;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
