package com.emma.thinkfast.controllers;

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

    @PostMapping("/createQuestion")
    public ResponseEntity<String> saveQuestion(@RequestBody Question question) {
        try {
            Question savedQuestion = questionRepo.save(question);
            return ResponseEntity.ok("Question saved: " + savedQuestion);
        } catch (Exception e) {
            return ResponseEntity.ok("Question not saved, exception encountered: " + e.getStackTrace());
        }
    }

    @GetMapping("/getQuestionById/{questionId}")
    public ResponseEntity<String> getQuestionById(@PathVariable String questionId) {
        return ResponseEntity.ok("stubbed getQuestionById");
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
