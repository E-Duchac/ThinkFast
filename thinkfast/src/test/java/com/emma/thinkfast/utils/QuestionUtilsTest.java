package com.emma.thinkfast.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;

class QuestionUtilsTest {

    private Question question = new Question();
    private Document document = new Document();

    @BeforeEach
    void setUp() {
        question.set_id("question-id");
        question.setQuestionText("Please give the equation known as the Pythagorean Theorem.");
        List<String> answerList = new ArrayList<String>();
        answerList.add("a^2 + b^2 = c^2");
        question.setAnswerText(answerList);
        question.setCategory(Category.MATHEMATICS);

        Map<String, Object> map = new HashMap<>();
        map.put("_id", question.get_id());
        map.put("questionText", question.getQuestionText());
        map.put("answerText", question.getAnswerText());
        map.put("category", question.getCategory());
        document.putAll(map);
    }

    @Test
    void testQuestionToDocument() {
        Document convertedDocument = QuestionUtils.questionToDocument(question);

        assertThat(convertedDocument).isEqualTo(document);
    }

    @Test
    void testDocumentToQuestion() {
        Question convertedQuestion = QuestionUtils.documentToQuestion(document);

        assertThat(convertedQuestion).isEqualTo(question);
    }
}
