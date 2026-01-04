package com.pk.platform.securitylib.service.impl;

import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.repository.UserIdentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserIdentityDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserIdentityDetailService.class);
    private final UserIdentityRepository userIdentityRepository;

    public UserIdentityDetailService(UserIdentityRepository userIdentityRepository) {
        this.userIdentityRepository = userIdentityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserIdentity userDetails = userIdentityRepository.findByName(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        return User
                .builder()
                .username(userDetails.getName())
                .password(userDetails.getPassword())
                .roles(userDetails.getRoles())
                .build();
    }
}
