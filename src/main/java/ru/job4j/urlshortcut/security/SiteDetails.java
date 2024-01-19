package ru.job4j.urlshortcut.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.job4j.urlshortcut.model.Site;

import java.util.Collection;

@Component
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
        return site.getLogin();
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

    public Site getSite() {
        return this.site;
    }

}
