package com.example.simplefirebaseandroid.ui.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private LoginService loginService = LoginService.getInstance();

    @Test
    public void fieldsAreNotInitialized() {
        assertEquals(loginService.fieldsAreValid("",""),"Password is empty or has less than 6 characters!");
        assertEquals(loginService.fieldsAreValid("","something"), "Email does not have the correct format!");
    }

    @Test
    public void emailIsNotValid(){
        assertEquals(loginService.fieldsAreValid("testingEmails","something"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("testingEmails@","something"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("testingEmails@gmail","something"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("testingEmails@.com","something"), "Email does not have the correct format!");
    }

    @Test
    public void passwordIsNotValid()
    {
        assertEquals(loginService.fieldsAreValid("testingEmails@gmail.com","t"),"Password is empty or has less than 6 characters!");
        assertEquals(loginService.fieldsAreValid("testingEmails@gmail.com","tests"),"Password is empty or has less than 6 characters!");

    }

    @Test
    public void fieldsAreValid()
    {
        assertEquals(loginService.fieldsAreValid("testingEmails@gmail.com","testsWork"),"ok");
    }

}