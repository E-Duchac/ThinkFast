package com.emma.thinkfast.services;

import java.util.List;

import com.emma.thinkfast.models.Question;

public interface QuestionService {
    public List<Question> scrambleTrim(List<Question> questionList, int noOfQuestions);
    public Question saveQuestion(Question question);
    public Question getQuestionById(String questionId);
    public List<Question> getQuestionsByCategory(String category);
    public Question updateQuestion(Question question);
    public Question deleteQuestion(String questionId);
}
