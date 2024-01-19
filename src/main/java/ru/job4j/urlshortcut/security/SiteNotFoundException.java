package ru.job4j.urlshortcut.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SiteNotFoundException extends UsernameNotFoundException {

    public SiteNotFoundException(String url) {
        super(url);
    }

}
