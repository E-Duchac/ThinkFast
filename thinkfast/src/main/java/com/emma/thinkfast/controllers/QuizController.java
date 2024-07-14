package com.emma.thinkfast.controllers;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

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

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Quiz;
import com.emma.thinkfast.repositories.QuizRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    /*
    private final QuizRepository quizRepo;
    private static final Logger logger = Logger.getLogger(QuizController.class.getName());

    @Autowired
    public QuizController(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }
        
    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz savedQuiz = quizRepo.save(quiz);
            logger.log(Level.INFO, "Quiz saved: {}", savedQuiz);
            ObjectMapper obMap = new ObjectMapper();
            return ResponseEntity.ok(obMap.writeValueAsString(savedQuiz));
        } catch (Exception e) {
            logger.log(Level.WARNING, "Quiz not saved, exception encountered: {}", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Quiz not saved, exception encountered: {}" + e.getStackTrace());
        }
    }

    @GetMapping("/getQuizById/{quizId}")
    public ResponseEntity<String> getQuizById(@PathVariable String quizId) {
        try {
            Optional<Quiz> optQuiz = quizRepo.findById(quizId);
            if (optQuiz.isEmpty()) {
                throw new EntityNotFoundException();
            }
            else {
                Quiz quiz = optQuiz.get();
                logger.log(Level.INFO, "Quiz found: {}", quiz);
                ObjectMapper obMap = new ObjectMapper();
                return ResponseEntity.ok(obMap.writeValueAsString(quiz));
            }
        } catch (EntityNotFoundException enfe) {
            logger.log(Level.WARNING, "Quiz not found with id: {}", quizId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Quiz not found with id: " + quizId);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unknown exception: {}", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Quiz not found, unknown exception: " + e.getStackTrace());
        }
    }
    */

    @GetMapping("/listQuizzesByCategory/{category}")
    public ResponseEntity<String> listQuizzesByCategory(@PathVariable String category) {
        return ResponseEntity.ok("stubbed listQuizzesByCategory");
    }

    @PutMapping("/updateQuiz/{quizId}")
    public ResponseEntity<String> updateQuiz(@PathVariable String quizId, @RequestBody Quiz newQuizDoc) {
        return ResponseEntity.ok("stubbed updateQuiz");
    }

    @DeleteMapping("/deleteQuiz/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable String quizId) {
        return ResponseEntity.ok("stubbed deleteQuiz");
    }
}
