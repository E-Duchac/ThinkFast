package com.emma.thinkfast.utils;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;

public class QuestionUtils {
    private QuestionUtils () {}
    
    public static Document questionToDocument(Question question) {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", question.get_id());
        map.put("questionText", question.getQuestionText());
        map.put("answerText", question.getAnswerText());
        map.put("category", question.getCategory());
        return new Document(map);
    }

    public static Question documentToQuestion(Document document) {
        Question question = new Question();
        question.set_id(document.getString("_id"));
        question.setQuestionText(document.getString("questionText"));
        question.setAnswerText(document.getList("answerText", String.class));
        question.setCategory(Enum.valueOf(Category.class, document.get("category").toString()));
        return question;
    }
}
