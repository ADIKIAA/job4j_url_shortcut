package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Shortcut;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ShortcutRepository extends CrudRepository<Shortcut, Integer> {

    Optional<Shortcut> findByShortcut(String shortcut);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update shortcuts set count = count + 1 where id = :id", nativeQuery = true)
    void incrementCount(int id);

    @Query(value = "SELECT shortcut FROM shortcuts WHERE site_id = :siteId AND url = :url", nativeQuery = true)
    String findCodeBySiteIdAndUrl(int siteId, String url);

}
