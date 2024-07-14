package com.emma.thinkfast.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionRepository questionRepo;
    private static final Logger logger = Logger.getLogger(QuestionController.class.getName());

    @Autowired
    public QuestionController(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    /* Considering not exposing this endpoint at all; why create questions 
     * one-by-one instead of uploading whole .csv files to the database?
     */
    @PostMapping("/createQuestion")
    public ResponseEntity<String> saveQuestion(@RequestBody Question question) {
        try {
            Question savedQuestion = questionRepo.save(question);
            logger.log(Level.INFO, "Question saved: {}", question);
            ObjectMapper obMap = new ObjectMapper();
            return ResponseEntity.ok(obMap.writeValueAsString(savedQuestion));
        } catch (Exception e) {
            logger.log(Level.WARNING, "Question not saved, exception encountered: ", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Question not saved, exception encountered: " + e.getStackTrace());
        }
    }

    @GetMapping("/getQuestionById/{questionId}")
    public ResponseEntity<String> getQuestionById(@PathVariable String questionId) {
        try {
            Optional<Question> question = questionRepo.findById(questionId);
            logger.log(Level.INFO, "Question found: {}", question);
            return ResponseEntity.ok(question.toString());
        }
        catch (NullPointerException npe) {
            logger.log(Level.WARNING, "Question with id {} not found: ", questionId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Question by id " + questionId + " not found: " + npe.getStackTrace());
        }
    }

    @GetMapping("/getAllQuestionsByCategory/{category}")
    public ResponseEntity<String> getAllQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok("stubbed getQuestionByCategory");
    }

    @PutMapping("/updateQuestion/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable String questionId, @RequestBody Question newQuestionDoc) {
        return ResponseEntity.ok("stubbed updateQuestion");
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String questionId) {
        return ResponseEntity.ok("stubbed deleteQuestion");
    }
}
