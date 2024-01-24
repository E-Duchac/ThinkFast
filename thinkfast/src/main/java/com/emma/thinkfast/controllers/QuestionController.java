package com.emma.thinkfast.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionRepository questionRepo;

    @Autowired
    public QuestionController(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    @PostMapping("/saveQuestion")
    public ResponseEntity<String> saveQuestion(@RequestBody Question question) {
        try {
            Question savedQuestion = questionRepo.save(question);
            return ResponseEntity.ok("Question saved: " + savedQuestion);
        } catch (Exception e) {
            return ResponseEntity.ok("Question not saved, exception encountered: " + e.getStackTrace());
        }
    }
}
