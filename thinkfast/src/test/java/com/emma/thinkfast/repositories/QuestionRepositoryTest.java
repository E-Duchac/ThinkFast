package com.emma.thinkfast.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.utils.QuestionUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataMongoTest
class QuestionRepositoryTest {
    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;
    @Mock
    private MongoCollection<Document> collection;

    private QuestionRepository questionRepo;
    private FindIterable<Document> findIterable;
    private MongoCursor<Document> cursor;
    private Question question;
    private Document questionDoc;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString())).thenReturn(collection);
        questionRepo = new QuestionRepository(mongoClient);

        question = new Question();
        question.set_id("ignore-me-test-id");
        question.setQuestionText("What style of art was Pablo Picasso best known for?");
        List<String> answerList = new ArrayList<String>();
        answerList.add("Cubism");
        question.setAnswerText(answerList);
        question.setCategory(Enum.valueOf(Category.class, "ARTS_HUMANITIES"));

        questionDoc = QuestionUtils.questionToDocument(question);

        findIterable = mock(FindIterable.class);
        when(collection.insertOne(any(Document.class))).thenReturn(mock(InsertOneResult.class));
    }

    @AfterEach
    void tearDown() {
        collection.drop(); //clean up after each test
    }

    @Test
    void testSave() {
        Question savedQuestion = questionRepo.save(question);
        
        assertThat(savedQuestion).isNotNull().isEqualTo(question);
    }

    @Test
    void testFindById() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(questionDoc);

        questionRepo.save(question);
        Question foundQuestion = questionRepo.findById(question.get_id()).get();

        assertThat(foundQuestion).isNotNull().isEqualTo(question);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testFindByCategory() {
        cursor = mock(MongoCursor.class);
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(questionDoc);

        questionRepo.save(question);
        List<Question> foundQuestionList = questionRepo.findByCategory("ARTS_HUMANITIES");

        assertThat(foundQuestionList).isNotNull().isNotEmpty();
        assertThat(foundQuestionList.get(0)).isEqualTo(question);
    }

    @Test
    void testUpdateById() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(questionDoc);
        questionRepo.save(question);

        question.setQuestionText("What is the name of Pablo Picasso?");
        when(collection.findOneAndReplace(any(Document.class), any(Document.class)))
            .thenReturn(QuestionUtils.questionToDocument(question));
        Question updatedQuestion = questionRepo.updateById(question).get();

        assertThat(updatedQuestion).isNotNull().isEqualTo(question);
        assertThat(updatedQuestion.getQuestionText()).isEqualTo("What is the name of Pablo Picasso?");
    }

    @Test
    void testDeleteById() {
        when(collection.findOneAndDelete(any(Document.class))).thenReturn(questionDoc);
        questionRepo.save(question);

        Question deletedQuestion = questionRepo.deleteById(question.get_id()).get();

        assertThat(deletedQuestion).isNotNull().isEqualTo(question);
    }
}
