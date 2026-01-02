package com.pk.platform.securitylib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecuritylibApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritylibApplication.class, args);
	}
}
