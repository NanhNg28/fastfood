package com.nanhng.FastFood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtToKenProvider {
    private final String SECRET = "1d43f0b45beb3a053007470d2f751732b3e6a760354991c414606723578508c18605adacd722b8e907b239041ff2173c03713a159506b540dc74aed033ffefbd2e976d61bbfd04c516040f45549acd0bba9171302752db6103bb2b2f90b90ba3969f309fbc6073d0501897ab4c79c455d733e31bf7cbed44911166330b0c2a24eb1c159f28d9a07b5c47d54980d56db265f2e402cafa80735c08744ea1bbce7c54e20501a6af7d285fc990694b1452fd7853dd7bd99f8bab88543ce0cf13d0ef5654849188d8db34752c6f91e9bc1d415f89a8510589ca43b83182b8912a61a47c281647d6a24998b11a7b825e831f4f808b569f026b047cc5e8ae06dcdd21fa";
    @Value("${app.jwtAdminExpirationInMs}")
    private int jwtExpirationInMs;
    public String generateToken(final int id) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .subject(String.valueOf(id))
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    private Claims getPayLoad(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Integer getSubIdFromToken(String token) {
        return Integer.parseInt(getPayLoad(token).getSubject());
    }

    public boolean validateJwt(String jwt) {
        return getPayLoad(jwt).getExpiration().after(Date.from(Instant.now()));
    }

}
