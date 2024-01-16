package ru.job4j.url_shortcut.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthorizationDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

}
