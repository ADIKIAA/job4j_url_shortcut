package ru.job4j.urlshortcut.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import ru.job4j.urlshortcut.model.Site;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    public static final String SECRET = "JWT__Secret__";
    public static final Date EXPIRATION_TIME = Date.from(ZonedDateTime.now().plusHours(2).toInstant());

    public String generateToken(Site site) {
        return JWT.create()
                .withSubject("Site Details")
                .withClaim("login", site.getLogin())
                .withIssuedAt(new Date())
                .withIssuer("shortcut_app")
                .withExpiresAt(EXPIRATION_TIME)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject("Site Details")
                .withIssuer("shortcut_app")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("login").asString();
    }

}
