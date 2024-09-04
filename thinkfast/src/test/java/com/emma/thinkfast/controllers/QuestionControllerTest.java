package com.emma.thinkfast.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.emma.thinkfast.dtos.CustomQuizRequest;
import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.services.QuestionServiceImpl;

class QuestionControllerTest {
    @Mock
    private QuestionServiceImpl questionService;

    private QuestionController questionController;

    private Question question;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        questionController = new QuestionController(questionService);

        question = new Question();
        question.set_id("ignore-me-question-id");
        question.setQuestionText("Alexander the Great remarked of this historical figure that if he could not be Alexander the Great, he would be this man, a personal acquaintence. In return, this man responded that if he could not be himself, he would <i>also</i> choose to be himself. Which Ancient Greek philosopher was best known for his search \"for an honest man\"?");
        List<String> answerList = new ArrayList<String>();
        answerList.add("Diogenes");
        question.setAnswerText(answerList);
        question.setCategory(Category.SOCIAL_STUDIES);
    }

    @Test
    void testSaveQuestion() {
        ResponseEntity<String> responseEntity = questionController.saveQuestion(question);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetQuestionById() {
        when(questionService.getQuestionById(question.get_id())).thenReturn(question);

        ResponseEntity<String> responseEntity = questionController.getQuestionById(question.get_id());

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetQuestionsByCategory() {
        List<Question> questionsByCategory = new ArrayList<Question>();
        questionsByCategory.add(question);

        when(questionService.getQuestionsByCategory("SOCIAL_STUDIES")).thenReturn(questionsByCategory);
    
        ResponseEntity<List<Question>> responseEntity = questionController.getAllQuestionsByCategory("SOCIAL_STUDIES");
        
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetQuestionsByMultiCategory() {
        CustomQuizRequest customRequest = new CustomQuizRequest();
        customRequest.setCategories(new String[]{"SOCIAL_STUDIES"});
        customRequest.setQuizLength(5);

        List<Question> questionsByCategory = new ArrayList<Question>();
        questionsByCategory.add(question);

        when(questionService.getQuestionsByCategory("SOCIAL_STUDIES")).thenReturn(questionsByCategory);

        ResponseEntity<List<Question>> responseEntity = questionController.getAllQuestionsByCategory("SOCIAL_STUDIES");
        
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUpdateQuestion() {
        when(questionService.updateQuestion(question)).thenReturn(question);

        ResponseEntity<String> responseEntity = questionController.updateQuestion(question.get_id(), question);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testDeleteQuestion() {
        when(questionService.deleteQuestion(question.get_id())).thenReturn(question);
        
        ResponseEntity<String> responseEntity = questionController.deleteQuestion(question.get_id());

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}