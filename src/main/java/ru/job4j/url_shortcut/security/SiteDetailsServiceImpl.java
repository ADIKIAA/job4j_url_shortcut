package ru.job4j.url_shortcut.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Site;
import ru.job4j.url_shortcut.repository.SiteRepository;

@Service
@AllArgsConstructor
public class SiteDetailsServiceImpl implements UserDetailsService {

    private SiteRepository siteRepository;

    @Override
    public UserDetails loadUserByUsername(String url) throws UsernameNotFoundException {
        Site site = siteRepository.findByUrl(url);
        if (site == null) {
            throw new SiteNotFoundException(url);
        }
        return new SiteDetails(site);
    }

}
