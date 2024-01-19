package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Shortcut;
import ru.job4j.urlshortcut.repository.ShortcutRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShortcutService {
    
    private final ShortcutRepository shortcutRepository;
    
    public Optional<Shortcut> findByCode(String shortcut) {
        return shortcutRepository.findByShortcut(shortcut);
    }

    public void incrementCount(int id) {
        shortcutRepository.incrementCount(id);
    }

    public List<Shortcut> findAll() {
        return (List<Shortcut>) shortcutRepository.findAll();
    }

}
