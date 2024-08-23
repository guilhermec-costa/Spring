package com.me.SpringApp.application.usecase;

public class Login {

    public String execute(String username, String password)
    {
        final String token = username + password;
        return token;
    }
}
