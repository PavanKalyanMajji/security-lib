package com.pk.platform.securitylib.service;

import com.pk.platform.securitylib.entity.UserIdentity;

public interface JwtService {
    String generateJwtToken(UserIdentity userIdentity);
}
