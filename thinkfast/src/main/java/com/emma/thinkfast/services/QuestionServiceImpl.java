package com.emma.thinkfast.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;

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
    
    @Override
    public Question saveQuestion(Question question) {
        try {
            Question savedQuestion = questionRepo.save(question);
            logger.log(Level.INFO, "Question saved: {}", savedQuestion);
            return savedQuestion;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to save a new Question with id {0}", question.get_id());
            throw e;
        }
    }

    @Override
    public Question getQuestionById(String questionId) {
        try {
            Question getQuestion = questionRepo.findById(questionId).get();
            logger.log(Level.INFO, "Question with id {0} retrieved", questionId);
            return getQuestion;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "Question with id {0} not found", questionId);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve Question with id {0}", questionId);
            throw e;
        }
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        try {
            List<Question> questionList = questionRepo.findByCategory(category);
            logger.log(Level.INFO, "All {0} category questions retrieved", category);
            return questionList;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve any {0} category questions", category);
            throw e;
        }
    }

    @Override
    public Question updateQuestion(Question question) {
        try {
            Question updateQuestion = questionRepo.updateById(question).get();
            logger.log(Level.INFO, "Question with id {0} updated", question.get_id());
            return updateQuestion;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "Question with id {0} not found", question.get_id());
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update Question with id {0}", question.get_id());
            throw e;
        }
    }

    @Override
    public Question deleteQuestion(String questionId) {
        try {
            Question deleteQuestion = questionRepo.deleteById(questionId).get();
            logger.log(Level.INFO, "Question with id {0} deleted", questionId);
            return deleteQuestion;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "Question with id {0} not found", questionId);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete Question with id {0}", questionId);
            throw e;
        }
    }
}
