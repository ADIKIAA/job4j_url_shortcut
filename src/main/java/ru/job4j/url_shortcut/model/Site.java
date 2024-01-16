package ru.job4j.url_shortcut.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sites")
@Data
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String login;

    private String password;

}
