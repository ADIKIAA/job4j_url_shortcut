package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.AuthorizationDTO;
import ru.job4j.urlshortcut.dto.StatisticDTO;
import ru.job4j.urlshortcut.model.Shortcut;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.security.JWTUtil;
import ru.job4j.urlshortcut.service.ShortcutService;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/shortcut")
public class SiteController {

    private final SiteService siteService;

    private final JWTUtil jwtUtil;

    private final ShortcutService shortcutService;

    @PostMapping
    @RequestMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody Map<String, String> req) {
        String url = req.get("site");
        Optional<Site> optionalSite = siteService.findByUrl(url);
        boolean registered = false;
        Site site;
        if (optionalSite.isEmpty()) {
            site = siteService.save(url);
            registered = true;
        } else {
            site = optionalSite.get();
        }
        Object body = Map.of(
                "registration ", registered,
                "login", site.getLogin(),
                "password ", site.getPassword()
        );
        return new ResponseEntity<>(
                body,
                HttpStatus.OK
        );
    }

    @PostMapping
    @RequestMapping("/authorization")
    public ResponseEntity<Void> authorization(@RequestBody @Valid AuthorizationDTO auth) {
        Optional<Site> optionalSite = siteService.findByLoginAndPassword(auth.getLogin(), auth.getPassword());
        if (optionalSite.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String token = jwtUtil.generateToken(optionalSite.get());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", String.format("Bearer %s", token)).build();
    }

    @PostMapping
    @RequestMapping("/convert")
    public ResponseEntity<Object> convert(@RequestBody Map<String, String> req,
                                          @RequestHeader(name = "Authorization") String header) {

        String token = header.toString().replace("Bearer ", "");
        String login = jwtUtil.validateTokenAndRetrieveClaim(token);

        String url = req.get("url");
        if (url == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String shortcut = siteService.addShortcut(login, url);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("code", shortcut));
    }

    @GetMapping
    @RequestMapping("/redirect/{code}")
    public ResponseEntity<Object> redirect(@PathVariable(name = "code") String code) {

        Optional<Shortcut> optionalShortcut = shortcutService.findByCode(code);
        if (optionalShortcut.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        shortcutService.incrementCount(optionalShortcut.get().getId());
        return ResponseEntity
                .status(HttpStatus.valueOf(302))
                .header("HTTP CODE", String.format("302 REDIRECT %s", optionalShortcut.get().getUrl()))
                .build();
    }

    @GetMapping
    @RequestMapping("/statistic")
    public ResponseEntity<Object> statistic() {

        List<StatisticDTO> statistic = shortcutService.findAll().stream()
                .map(this::convertShortcutToStatisticDTO).toList();

        List<Object> body = new ArrayList<>();
        body.add(new StatisticDTO("URL", 0));
        body.addAll(statistic);
        return new ResponseEntity<>(
                body,
                HttpStatus.OK
        );
    }

    private StatisticDTO convertShortcutToStatisticDTO(Shortcut shortcut) {
        return new StatisticDTO(shortcut.getUrl(), shortcut.getCount());
    }

}
