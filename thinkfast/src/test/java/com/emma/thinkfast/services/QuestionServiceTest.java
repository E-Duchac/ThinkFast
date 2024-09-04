package com.emma.thinkfast.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepo;

    private QuestionServiceImpl questionService;
    private Question question;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionServiceImpl(questionRepo);

        question = new Question();
        question.set_id("ignore-me-test-id");
        question.setQuestionText("What is the only mammal capable of true flight?");
        List<String> answerList = new ArrayList<String>();
        answerList.add("Bat");
        question.setAnswerText(answerList);
        question.setCategory(Enum.valueOf(Category.class, "SCIENCE"));
    }

    @Test
    void testScrambleTrim() {
        Question q1 = new Question();
        Question q2 = new Question();
        Question q3 = new Question();
        q1.set_id("question-1");
        q2.set_id("question-2");
        q3.set_id("question-3");
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);

        List<Question> scrambleTrimmedList = questionService.scrambleTrim(questionList, 3);
        
        //Retry functionality. It's not a problem for the application if the scrambleTrim method occasionally
        //returns the same order (though it would be trememdously unlikely as the question list grows).
        //However, if this happens in a test, the test should be rerun to verify that scrambling is still
        //happening and is not broken.
        int maxRetries = 3;
        boolean isScrambled = false;
        for (int i = 0; i < maxRetries; i++) {
            if (!scrambleTrimmedList.equals(questionList)) {
                isScrambled = true;
                break;
            }
            scrambleTrimmedList = questionService.scrambleTrim(questionList, 3);
        }
        
        assertThat(isScrambled).isTrue();
        assertThat(scrambleTrimmedList)
            .containsExactlyInAnyOrderElementsOf(questionList)
            .doesNotContainSequence(questionList);
    }

    @Test
    void testSaveQuestion() {
        when(questionRepo.save(question)).thenReturn(question);

        Question savedQuestion = questionService.saveQuestion(question);

        assertThat(savedQuestion).isEqualTo(question);
    }

    @Test
    void testGetQuestionById() {
        when(questionRepo.findById(question.get_id())).thenReturn(Optional.of(question));
        
        questionService.saveQuestion(question);

        Question foundQuestion = questionService.getQuestionById(question.get_id());

        assertThat(foundQuestion).isEqualTo(question);
    }

    @Test
    void getQuestionsByCategory() {
        List<Question> scienceQuestions = new ArrayList<Question>();
        scienceQuestions.add(question);
        when(questionRepo.findByCategory("SCIENCE")).thenReturn(scienceQuestions);

        questionService.saveQuestion(question);

        List<Question> foundQuestions = questionService.getQuestionsByCategory("SCIENCE");

        assertThat(foundQuestions).isNotNull().isNotEmpty();
        assertThat(foundQuestions.get(0)).isEqualTo(question);
    }

    @Test
    void testUpdateQuestion() {
        questionService.saveQuestion(question);

        question.setQuestionText("What homonym refers to both a blunt wooden implement used for striking, and a small flying mammal?");
        when(questionRepo.updateById(question)).thenReturn(Optional.of(question));

        Question updatedQuestion = questionService.updateQuestion(question);

        assertThat(updatedQuestion.get_id()).isEqualTo(question.get_id());
        assertThat(updatedQuestion.getQuestionText()).isEqualTo("What homonym refers to both a blunt wooden implement used for striking, and a small flying mammal?");
        assertThat(updatedQuestion.getAnswerText()).isEqualTo(question.getAnswerText());
        assertThat(updatedQuestion.getCategory()).isEqualTo(question.getCategory());
    }

    @Test
    void testDeleteQuestion() {
        when(questionRepo.deleteById(question.get_id())).thenReturn(Optional.of(question));
        questionService.saveQuestion(question);

        Question deletedQuestion = questionService.deleteQuestion(question.get_id());

        assertThat(deletedQuestion).isEqualTo(question);
    }
}
