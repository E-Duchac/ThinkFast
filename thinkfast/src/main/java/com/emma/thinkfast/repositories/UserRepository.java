package com.emma.thinkfast.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emma.thinkfast.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
}
