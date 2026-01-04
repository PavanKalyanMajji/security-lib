package com.pk.platform.securitylib.service;

import com.pk.platform.securitylib.entity.UserIdentity;

public interface AuthenticationService {
    String userLogin(String userName,String password);

    UserIdentity registerUser(UserIdentity userIdentity);
}
