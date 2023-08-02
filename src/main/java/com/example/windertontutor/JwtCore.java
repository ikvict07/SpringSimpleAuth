package com.example.windertontutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtCore {
    @Value("${winderton-tutor.app.secret}")
    private String secret;

    @Value("${winderton-tutor.app.lifetime}")
    private int lifetime;

    public String generateToken(Authentication authentication) {
        UserDetailsImp userDetailsImp = (UserDetailsImp) (authentication.getPrincipal());
        return Jwts.builder().setSubject((userDetailsImp.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
    }

}
