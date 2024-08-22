package com.emma.thinkfast.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class QuestionRepositoryTest {
    @Autowired
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    private QuestionRepository questionRepo;

    private Question question;

    @BeforeEach
    void setUp() {
        //This is wrong! Need to properly mock the database.
        MongoDatabase database = mongoClient.getDatabase("test"); //test db
        collection = database.getCollection("questions");
        questionRepo = new QuestionRepository(mongoClient);

        question = new Question();
        question.set_id("ignore-me-test-id");
        question.setQuestionText("What style of art was Pablo Picasso best known for?");
        List<String> answerList = new ArrayList<String>();
        answerList.add("Cubism");
        question.setAnswerText(answerList);
        question.setCategory(Enum.valueOf(Category.class, "ARTS_HUMANITIES"));
    }

    @AfterEach
    void tearDown() {
        collection.drop(); //clean up after each test
    }

    @Test
    void testSave() {
        Question foundQuestion = questionRepo.save(question);

        assertThat(foundQuestion).isNotNull();
        assertThat(foundQuestion.get_id()).isEqualTo(question.get_id());
        assertThat(foundQuestion.getQuestionText()).isEqualTo(question.getQuestionText());
        assertThat(foundQuestion.getAnswerText()).isEqualTo(question.getAnswerText());
        assertThat(foundQuestion.getCategory()).isEqualTo(question.getCategory());
    }

    @Test
    public void testFindById() {
        questionRepo.save(question);
        Question foundQuestion = questionRepo.findById(question.get_id()).get();

        assertThat(foundQuestion).isNotNull();
        assertThat(foundQuestion.get_id()).isEqualTo(question.get_id());
        assertThat(foundQuestion.getQuestionText()).isEqualTo(question.getQuestionText());
        assertThat(foundQuestion.getAnswerText()).isEqualTo(question.getAnswerText());
        assertThat(foundQuestion.getCategory()).isEqualTo(question.getCategory());
    }

    @Test
    public void testFindByCategory() {
        
    }

    @Test
    public void testUpdateById() {

    }

    @Test
    public void testDeleteById() {
        //questionRepo.deleteById(null)
    }
}
