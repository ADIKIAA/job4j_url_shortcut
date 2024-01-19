package ru.job4j.urlshortcut.dto;

import javax.validation.constraints.NotBlank;

public class AuthorizationDTO {

    @NotBlank(message = "Login should not empty")
    private String login;

    @NotBlank (message = "Password should not null")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
