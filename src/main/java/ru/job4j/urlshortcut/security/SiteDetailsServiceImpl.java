package ru.job4j.urlshortcut.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
public class SiteDetailsServiceImpl implements UserDetailsService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteDetailsServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws SiteNotFoundException {
        Optional<Site> site = siteRepository.findByLogin(login);
        if (site.isEmpty()) {
            throw new SiteNotFoundException("Site not found");
        }
        return new SiteDetails(site.get());
    }

}
