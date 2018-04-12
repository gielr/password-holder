package com.github.gielr.model;

import java.util.Arrays;

public class PasswordEntry {
    private Long id;
    private String serviceName;
    private String login;
    private char[] password;

    public PasswordEntry(Long id, String serviceName, String login, char[] password) {
        this.id = id;
        this.serviceName = serviceName;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
