package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url_shortcut.model.Site;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {

    Site findByUrl(String url);

}
