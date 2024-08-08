package com.emma.thinkfast.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.emma.thinkfast.utils.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    public String authenticateUser(String userName, String pw) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(userName, pw)
        );

        return JwtUtils.generateToken(auth.getName());
    }
}
