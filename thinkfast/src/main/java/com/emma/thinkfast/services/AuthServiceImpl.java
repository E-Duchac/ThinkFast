package com.emma.thinkfast.services;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emma.thinkfast.utils.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String authenticateUser(String username, String rawPass, String encPass) throws AuthenticationException {
        if (!passwordEncoder.matches(rawPass, encPass)) {
            throw new AuthenticationException("Invalid Username or Password.");
        }
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, encPass)
        );
        return JwtUtils.generateToken(auth.getName());
    }
}
