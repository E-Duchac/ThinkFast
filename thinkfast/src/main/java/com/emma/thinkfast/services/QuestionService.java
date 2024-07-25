package com.emma.thinkfast.services;

import java.util.List;

import com.emma.thinkfast.models.Question;

public interface QuestionService {
    public List<Question> scrambleTrim(List<Question> questionList, int noOfQuestions);
}
