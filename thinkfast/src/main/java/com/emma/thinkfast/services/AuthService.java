package com.emma.thinkfast.services;

import javax.naming.AuthenticationException;

public interface AuthService {
    public String authenticateUser(String username, String rawPass, String encPass) throws AuthenticationException;
}