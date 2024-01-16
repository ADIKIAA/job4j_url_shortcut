package ru.job4j.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Site;
import ru.job4j.url_shortcut.repository.SiteRepository;

@Service
@AllArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;

    public Site findByUrl(String url) {
        return siteRepository.findByUrl(url);
    }

    public Site save(String site) {
        Site newSite = new Site();
        newSite.setUrl(site);
        newSite.setLogin(RandomStringUtils.randomAlphabetic(10));
        newSite.setPassword(RandomStringUtils.randomAlphabetic(10));
        return siteRepository.save(newSite);
    }

}
