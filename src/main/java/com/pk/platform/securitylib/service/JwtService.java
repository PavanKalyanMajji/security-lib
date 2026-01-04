package com.pk.platform.securitylib.service;

import com.pk.platform.securitylib.entity.UserIdentity;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateJwtToken(UserIdentity userIdentity);
    boolean validateJwtToken(String jwtToken);

    Claims getClaims(String jwtToken);
}
