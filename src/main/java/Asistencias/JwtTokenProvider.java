package Asistencias;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
 
@Service
public class JwtTokenProvider {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken(String email, String string) {
        String jwt = Jwts.builder()
                .setSubject(email)
                .claim("roles", string)
                .signWith(key)
                .compact();
        return jwt;
    }
}
