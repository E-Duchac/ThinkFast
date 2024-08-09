package com.emma.thinkfast.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.dtos.CustomQuizRequest;
import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;
import com.emma.thinkfast.services.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*") //Remember to reconfigure!
public class QuestionController {
    private final QuestionRepository questionRepo;
    private final QuestionServiceImpl questionService;
    private static final Logger logger = Logger.getLogger(QuestionController.class.getName());

    @Autowired
    public QuestionController(QuestionRepository questionRepo, QuestionServiceImpl questionService) {
        this.questionRepo = questionRepo;
        this.questionService = questionService;
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
            logger.log(Level.WARNING, "Save failed; exception encountered: ", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Save failed; exception encountered: " + e.getStackTrace());
        }
    }

    @GetMapping("/getQuestionById/{questionId}")
    public ResponseEntity<String> getQuestionById(@PathVariable String questionId) {
        try {
            Optional<Question> optQuestion = questionRepo.findById(questionId);
            Question question = optQuestion.get();
            logger.log(Level.INFO, "Question found: {}", question);
            return ResponseEntity.ok (question.toString());
        } catch (NullPointerException npe) {
            logger.log(Level.WARNING, "Fetch failed; question with id {0} not found: {1}", 
                new Object[]{questionId, Arrays.toString(npe.getStackTrace())});
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Fetch failed; question not found with id " + questionId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fetch failed; unexpected exception occured: {}", Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Unexpected exception occured. Please try again or reach out to notify us of issue.");
        }
    }

    @GetMapping("/getQuestionsByCategory/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
        List<Question> questionList = new ArrayList<>();
        try {
            questionList = questionRepo.findByCategory(category);
            logger.log(Level.INFO, "{0} {1} question(s) found: {2}", 
                new Object[]{questionList.size(), category, Arrays.toString(questionList.toArray())});
            //return ResponseEntity.ok(Arrays.toString(questionList.toArray()));
            //return new ResponseEntity<>(questionList, headers, HttpStatus.OK);
            return ResponseEntity.ok(questionList);
        } catch (NullPointerException npe) {
            logger.log(Level.WARNING, "Fetch failed; questions with category {0} not found: {1}",
                new Object[]{category, Arrays.toString(npe.getStackTrace())});
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fetch failed; question not found with category " + category);
            return new ResponseEntity<>(questionList, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fetch failed; unexpected exception occured: {0}", Arrays.toString(e.getStackTrace()));
            //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Unexpected exception occured. Please try again or reach out to notify us of issue.");
            return new ResponseEntity<>(questionList, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/composeCustomQuiz") //Not done yet, still needs error handling
    public ResponseEntity<List<Question>> getQuestionsByMultiCategory(@RequestBody CustomQuizRequest requestDTO) {
        List<Question> allResponses = new ArrayList<>();
        for (String category : requestDTO.getCategories()) {
            List<Question> bodyResponse = getAllQuestionsByCategory(category).getBody();
            if (bodyResponse != null && !bodyResponse.isEmpty()) {
                allResponses.addAll(bodyResponse);
            }
        }
        logger.log(Level.INFO, "Categories: {0}", requestDTO.getCategories());
        logger.log(Level.INFO, "Quiz length: {0}", requestDTO.getQuizLength());
        List<Question> scrambleTrimmedList = questionService.scrambleTrim(allResponses, requestDTO.getQuizLength());
        logger.log(Level.INFO, "Response body contents: {0}", scrambleTrimmedList);
        return ResponseEntity.ok(scrambleTrimmedList);
    }

    @PutMapping("/updateQuestion/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable String questionId, @RequestBody Question newQuestionDoc) {
        try {
            newQuestionDoc.set_id(questionId);
            Optional<Question> question = questionRepo.updateById(newQuestionDoc);
            logger.log(Level.INFO, "Question with id {0} updated to: {1}",
                new Object[]{questionId, newQuestionDoc});
            return ResponseEntity.ok("Question with id " + questionId + " updated to: " + question.get());
        } catch (NullPointerException npe) {
            logger.log(Level.WARNING, "Update failed; question with id {0} not found: {1}", 
                new Object[]{questionId, Arrays.toString(npe.getStackTrace())});
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Update failed; question not found with id " + questionId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Update failed; unexpected exception occured: {}", Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Update failed; unexpected exception occured. Please try again or reach out to notify us of issue.");
        }
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String questionId) {
        try {
            Optional<Question> question = questionRepo.deleteById(questionId);
            logger.log(Level.INFO, "Question with id {0} deleted: {1}", 
                new Object[]{questionId, question.get()});
            return ResponseEntity.ok(question.get().toString());
        } catch (NullPointerException npe) {
            logger.log(Level.WARNING, "Deletion failed; question with id {0} not found: {1}", 
                new Object[]{questionId, Arrays.toString(npe.getStackTrace())});
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Deletion failed; question not found with id " + questionId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Deletion failed; unexpected exception occured: {}", Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Deletion failed; unexpected exception occured. Please try again or reach out to notify us of issue.");
        }
    }
}
