package ru.job4j.urlshortcut.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sites")
@Data
@Component
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String login;

    private String password;

    @OneToMany(mappedBy = "site")
    private List<Shortcut> shortcuts;

}
