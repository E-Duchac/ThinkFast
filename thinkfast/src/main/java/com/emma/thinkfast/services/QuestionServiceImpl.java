package com.emma.thinkfast.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuestionServiceImpl implements QuestionService {
    private Random rand = new Random();
    private final QuestionRepository questionRepo;
    private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    public QuestionServiceImpl(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public List<Question> scrambleTrim(List<Question> questionList, int noOfQuestions) {
        List<Question> scrambleTrimmedList = new ArrayList<>();
        int scrambleTrimmedListIndex = 0;

        if (questionList.size() < noOfQuestions) {
            logger.log(
                Level.WARNING,
                "Requested list size is longer than number of fetched questions; list will be {0} long.",
                questionList.size());
            noOfQuestions = questionList.size();
        }

        while (scrambleTrimmedListIndex < noOfQuestions) {
            int randomIndex = rand.nextInt(questionList.size());
            if (!scrambleTrimmedList.contains(questionList.get(randomIndex))) {
                scrambleTrimmedList.add(questionList.get(randomIndex));
                scrambleTrimmedListIndex++;
            }
        }
        return scrambleTrimmedList;
    }
    
    public Question saveQuestion(Question question) {
        try {
            Question savedQuestion = questionRepo.save(question);
            logger.log(Level.INFO, "Question saved: {}", savedQuestion);
            return savedQuestion;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Save failed; exception encountered: ", e.getStackTrace());
            return new Question();
        }
    }

    //public Question 
}
