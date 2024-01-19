package ru.job4j.urlshortcut.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final SiteDetailsServiceImpl siteDetailsService;

    @Autowired
    public AuthProviderImpl(SiteDetailsServiceImpl siteDetailsService) {
        this.siteDetailsService = siteDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();

        UserDetails userDetails = siteDetailsService.loadUserByUsername(login);

        String password = authentication.getCredentials().toString();
        if (password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, List.of());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
