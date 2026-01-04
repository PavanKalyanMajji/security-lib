package com.pk.platform.securitylib.controller.impl;

import com.pk.platform.securitylib.controller.AuthenticationController;
import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.service.AuthenticationService;
import com.pk.platform.securitylib.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationService authenticationService;

    public AuthenticationControllerImpl(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<String> userLogin(String userName, String password) {
        return new ResponseEntity<>(
                authenticationService.userLogin(userName,password),
                HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<UserIdentity> registerUser(UserIdentity userIdentity) {
       userIdentity.setPassword(passwordEncoder.encode(userIdentity.getPassword()));
        return new ResponseEntity<>(authenticationService.registerUser(userIdentity),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getResponse() {
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
