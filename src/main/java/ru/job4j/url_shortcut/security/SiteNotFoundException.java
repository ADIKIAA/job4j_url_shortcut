package ru.job4j.url_shortcut.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SiteNotFoundException extends UsernameNotFoundException {

    public SiteNotFoundException(String url) {
        super(url);
    }

}
