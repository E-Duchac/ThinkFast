package com.emma.thinkfast.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emma.thinkfast.models.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    // public Quiz createQuiz();
    // public Quiz getQuizById(String _id);
    // public List<Quiz> listQuizzesByCategory(Category category);
    // public Quiz updateQuiz(String _id);
    // public void deleteQuiz(String _id);
}
