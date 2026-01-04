package com.pk.platform.securitylib.controller;

import com.pk.platform.securitylib.entity.UserIdentity;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {
    @GetMapping("/login")
    ResponseEntity<String> userLogin(@PathParam("userName") String userName,
                                     @PathParam("password") String password);

    @PostMapping("/register")
    ResponseEntity<UserIdentity> registerUser(@RequestBody UserIdentity userIdentity);

    @GetMapping("/test")
    ResponseEntity<String> getResponse();
}
