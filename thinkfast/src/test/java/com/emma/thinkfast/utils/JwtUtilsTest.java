package com.emma.thinkfast.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class JwtUtilsTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGenerateToken() {
        String jwts = JwtUtils.generateToken("username");

        assertThat(jwts).isNotEmpty();
    }

    @Test
    void testGetUsernameFromToken() {
        String jwts = JwtUtils.generateToken("testGenerateToken");
        String claimsSubject = JwtUtils.getUserNameFromToken(jwts);

        assertThat(claimsSubject).isNotEmpty().isEqualTo("testGenerateToken");
    }

    @Test
    void testValidateToken() {
        String jwts = JwtUtils.generateToken("testGenerateAnotherToken");
        boolean isValid = JwtUtils.validateToken(jwts);

        assertThat(isValid).isTrue();
    }
}
