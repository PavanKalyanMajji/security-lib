package com.pk.platform.securitylib.service.impl;

import com.pk.platform.securitylib.entity.UserIdentity;
import com.pk.platform.securitylib.repository.UserIdentityRepository;
import com.pk.platform.securitylib.service.AuthenticationService;
import com.pk.platform.securitylib.service.JwtService;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String userLogin(String userName,String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password));
        Optional<UserIdentity> userIdentityDetails = userIdentityRepository.findByName(userName);
        if(userIdentityDetails.isPresent()){
            return jwtService.generateJwtToken(userIdentityDetails.get());
        }else {
            throw new RuntimeException("No User Found");
        }
    }

    @Override
    public UserIdentity registerUser(UserIdentity userIdentity) {
        try {
            userIdentity = userIdentityRepository.save(userIdentity);
        } catch (JDBCException exception) {
            LOGGER.error("Error While Saving User Identity :{}",exception.getMessage());
            return userIdentity=null;
        }
        return userIdentity;
    }
}
