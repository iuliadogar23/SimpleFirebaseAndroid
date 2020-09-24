package com.example.simplefirebaseandroid.ui.signup;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpServiceTest {

    private SignUpService loginService = SignUpService.getInstance();

    @Test
    public void fieldsAreNotInitialized() {
        assertEquals(loginService.fieldsAreValid("", "", "",""),"Username is empty!");
        assertEquals(loginService.fieldsAreValid("username", "", "",""),"Password is empty or has less than 6 characters!");
        assertEquals(loginService.fieldsAreValid("username", "password", "",""),"Phone number does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("username", "password", "01236547895",""), "Email does not have the correct format!");
    }

    @Test
    public void emailIsNotValid(){
        assertEquals(loginService.fieldsAreValid("something","password", "0245167854","something"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("something","password", "0245167854","testingEmails@"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("something","password", "0245167854","something"), "Email does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("something","password", "0245167854","testingEmails@.com"), "Email does not have the correct format!");
    }

    @Test
    public void passwordIsNotValid()
    {
        assertEquals(loginService.fieldsAreValid("something","passw", "0245167854","something"), "Password is empty or has less than 6 characters!");
        assertEquals(loginService.fieldsAreValid("something","p", "0245167854","testingEmails@"), "Password is empty or has less than 6 characters!");

    }

    @Test
    public void usernameIsNoValid()
    {
        assertEquals(loginService.fieldsAreValid("", "password", "012456854","email@email.com"),"Username is empty!");

    }

    @Test
    public void phoneIsNotValid()
    {
        assertEquals(loginService.fieldsAreValid("something","password", "024?!5","testingEmails@gamil.com"), "Phone number does not have the correct format!");
        assertEquals(loginService.fieldsAreValid("something","password", "0245kjh854","testingEmails@gamil.com"), "Phone number does not have the correct format!");

    }

    @Test
    public void fieldsAreValid()
    {
        assertEquals(loginService.fieldsAreValid("something","password", "0245167854","testingEmails@gamil.com"), "ok");
    }
}