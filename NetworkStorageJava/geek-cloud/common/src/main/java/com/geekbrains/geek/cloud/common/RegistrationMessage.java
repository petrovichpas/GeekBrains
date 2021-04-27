package com.geekbrains.geek.cloud.common;

public class RegistrationMessage extends AbstractMessage{
    private String login;
    private String password;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationMessage(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
