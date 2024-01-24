package com.emma.thinkfast.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emma.thinkfast.models.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    
}
