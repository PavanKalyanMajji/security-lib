package com.pk.platform.securitylib.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface AuthenticationController {
    @GetMapping("/user/login")
    ResponseEntity<String> userLogin(@PathParam("userName") String userName,
                                     @PathParam("password") String password);
}
