package com.emma.thinkfast.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.utils.QuestionUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

@Component
public class QuestionRepository {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    @Autowired
    public QuestionRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getDatabase("ThinkFast").getCollection("question");
    }

    public Question save(Question question) {
        Document questionDoc = QuestionUtils.questionToDocument(question);
        collection.insertOne(questionDoc);
        return question;
    }

    public Optional<Question> findById(String questionId) {
        Document document = collection.find(new Document("_id", questionId)).first();
        return Optional.of(QuestionUtils.documentToQuestion(document));
    }

    public List<Question> findByCategory(String category) {
        List<Question> questionList = new ArrayList<>();
        FindIterable<Document> docList = collection.find(new Document("category", category));
        for (Document document : docList) {
            questionList.add(QuestionUtils.documentToQuestion(document));
        }
        return questionList;
    }

    public Optional<Question> updateById(Question question) {
        Document document = collection.findOneAndReplace(
            collection.find(new Document("_id", question.get_id())).first(), 
            QuestionUtils.questionToDocument(question));
        return Optional.of(QuestionUtils.documentToQuestion(document));
    }

    public Optional<Question> deleteById(String questionId) {
        Document document = collection.findOneAndDelete(new Document("_id", questionId));
        return Optional.of(QuestionUtils.documentToQuestion(document));
    }
}