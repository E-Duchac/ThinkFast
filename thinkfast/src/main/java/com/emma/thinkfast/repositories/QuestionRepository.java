package com.emma.thinkfast.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emma.thinkfast.models.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
    // public Question createQuestion();
    // public Question getQuestion(String quiz_id, String question_id);
    // public Question updateQuestion(String _id);
    // public void deleteQuestion(String _id);
}