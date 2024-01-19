package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Shortcut;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.ShortcutRepository;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;

    private final ShortcutRepository shortcutRepository;

    public Optional<Site> findByUrl(String url) {
        return siteRepository.findByUrl(url);
    }

    public Optional<Site> findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }

    public Optional<Site> findByLoginAndPassword(String login, String password) {
        return siteRepository.findByLoginAndPassword(login, password);
    }

    public Site save(String site) {
        Site newSite = new Site();
        newSite.setUrl(site);
        newSite.setLogin(RandomStringUtils.randomAlphabetic(10));
        newSite.setPassword(RandomStringUtils.randomAlphabetic(10));
        return siteRepository.save(newSite);
    }

    public String addShortcut(String login, String url) {
        Site site = siteRepository.findByLogin(login).get();

        String codeUrl = shortcutRepository.findCodeBySiteIdAndUrl(site.getId(), url);
        if (codeUrl != null) {
            return codeUrl;
        }

        Shortcut shortcut = new Shortcut();
        shortcut.setSite(site);
        shortcut.setUrl(url);
        codeUrl = RandomStringUtils.randomAlphabetic(7);
        shortcut.setShortcut(codeUrl);

        shortcutRepository.save(shortcut);
        site.getShortcuts().add(shortcut);
        return codeUrl;
    }

}
