package com.geekbrains.geek.cloud.common;

public class AuthMessage extends AbstractMessage{
    private String login;
    private String password;
    private String message;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public AuthMessage() {
    }

    public AuthMessage(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthMessage(String message) {
        this.message = message;
    }
}
