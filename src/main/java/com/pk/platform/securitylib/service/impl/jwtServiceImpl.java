package com.pk.platform.securitylib.service.impl;

import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class jwtServiceImpl implements JwtService {
    @Value("${jwt.secrete.key}")
    private String secreteKey;
    @Value("${jwt.expire.date.ms}")
    private long expireDateInMs;

    /**
     * generate Jwt Token
     * @param userIdentity
     * @return
     */
    @Override
    public String generateJwtToken(UserIdentity userIdentity){
        return Jwts
                .builder()
                .signWith(getKey(secreteKey))
                .subject(userIdentity.getName())
                .issuer("PK")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expireDateInMs))
                .claim("role",userIdentity.getRoles()).compact();
    }

    /**
     * Get Key By using Secrete Key
     * @param secreteKey pass the secrete Key
     * @return Key
     */
    public Key getKey(String secreteKey) {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secreteKey));
    }
}
