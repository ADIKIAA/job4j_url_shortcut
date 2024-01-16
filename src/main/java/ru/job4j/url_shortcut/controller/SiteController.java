package ru.job4j.url_shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.dto.AuthorizationDTO;
import ru.job4j.url_shortcut.model.Site;
import ru.job4j.url_shortcut.service.SiteService;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/shortcut")
public class SiteController {

    private final SiteService siteService;

    @PostMapping
    @RequestMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody Map<String, String> req) {
        String url = req.get("site");
        Site site = siteService.findByUrl(url);
        boolean registered = false;
        if (site == null) {
            siteService.save(url);
            registered = true;
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
    public ResponseEntity<Void> authorization(@RequestBody AuthorizationDTO auth) {
//        String login = auth.get("login");
//        String password = auth.get("password");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequestMapping("/convert")
    public ResponseEntity<Object> convert(@PathVariable(name = "login") String login,
                                                @PathVariable(name = "password") String password) {

    }

}
