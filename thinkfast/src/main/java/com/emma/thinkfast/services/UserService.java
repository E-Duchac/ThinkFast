package com.emma.thinkfast.services;

import com.emma.thinkfast.models.User;

public interface UserService {
    public User registerNewUser(User user);
    public User getUserById(String userId);
    public User getUserByEmail(String email);
    public User getUserByUsername(String username);
    public User updateUser(User user);
    public User deleteUser(String userId);
}
