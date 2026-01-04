package com.pk.platform.securitylib.service.impl;

import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class jwtServiceImpl implements JwtService {
    @Value("${jwt.secrete.key}")
    private String secreteKey;
    @Value("${jwt.expire.date.ms}")
    private long expireDateInMs;

    /**
     * generate Jwt Token
     *
     * @param userIdentity
     * @return
     */
    @Override
    public String generateJwtToken(UserIdentity userIdentity) {
        return Jwts
                .builder()
                .signWith(getSecreteKey(secreteKey))
                .subject(userIdentity.getName())
                .issuer("PK")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireDateInMs))
                .claim("role", userIdentity.getRoles()).compact();
    }

    /**
     * validate Jwt Token
     *
     * @param jwtToken pass the Jwt Token as argument
     * @return true if jwt not expired
     */
    @Override
    public boolean validateJwtToken(String jwtToken) {
        Claims payLoad = getClaims(jwtToken);
        return Objects.nonNull(payLoad);
    }

    /**
     * get Claims
     *
     * @param jwtToken pass jwt as an argument
     * @return Claims
     */
    @Override
    public Claims getClaims(String jwtToken) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSecreteKey(secreteKey))
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (SignatureException exception) {
            throw new RuntimeException("Jwt Validation failed: " + exception.getMessage());
        }
    }

    /**
     * Get Key By using Secrete Key
     *
     * @param secreteKey pass the secrete Key
     * @return Key
     */
    public SecretKey getSecreteKey(String secreteKey) {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secreteKey));
    }
}
