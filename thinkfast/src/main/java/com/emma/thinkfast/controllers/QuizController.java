package com.emma.thinkfast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizRepository quizRepo;

    @Autowired
    public QuizController(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok("stubbed createQuiz");
    }

    @GetMapping("/getQuizById/{quizId}")
    public ResponseEntity<String> getQuizById(@PathVariable String quizId) {
        return ResponseEntity.ok("stubbed getQuizById");
    }

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
