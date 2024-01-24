package com.emma.thinkfast.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emma.thinkfast.models.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
    
}