package com.pk.platform.securitylib.controller.impl;

import com.pk.platform.securitylib.controller.AuthenticationController;
import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final JwtService jwtService;

    public AuthenticationControllerImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<String> userLogin(String userName, String password) {
        return new ResponseEntity<>(
                jwtService.generateJwtToken(
                        new UserIdentity(
                                UUID.randomUUID(),
                                userName,password,"ADMIN" )),
                HttpStatus.FOUND);
    }
}
