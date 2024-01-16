package ru.job4j.url_shortcut.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.url_shortcut.model.Site;

import java.util.Collection;

@AllArgsConstructor
public class SiteDetails implements UserDetails {

    private final Site site;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return site.getPassword();
    }

    @Override
    public String getUsername() {
        return site.getUrl();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
