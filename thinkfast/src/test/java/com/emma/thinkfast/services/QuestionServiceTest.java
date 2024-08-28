package com.emma.thinkfast.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.models.Question;
import com.emma.thinkfast.repositories.QuestionRepository;
import com.emma.thinkfast.utils.QuestionUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QuestionServiceTest {
    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;
    @Mock
    private MongoCollection<Document> collection;

    private QuestionRepository questionRepo;
    private QuestionServiceImpl questionService;
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
        questionService = new QuestionServiceImpl(questionRepo);

        question = new Question();
        question.set_id("ignore-me-test-id");
        question.setQuestionText("What is the only mammal capable of true flight?");
        List<String> answerList = new ArrayList<String>();
        answerList.add("Bat");
        question.setAnswerText(answerList);
        question.setCategory(Enum.valueOf(Category.class, "SCIENCE"));

        questionDoc = QuestionUtils.questionToDocument(question);

        findIterable = mock(FindIterable.class);
        cursor = mock(MongoCursor.class);
        when(collection.insertOne(any(Document.class))).thenReturn(mock(InsertOneResult.class));
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(questionDoc);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(questionDoc);
        when(collection.findOneAndDelete(any(Document.class))).thenReturn(questionDoc);
    }

    @AfterEach
    void tearDown() {
        collection.drop();
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

        assertThat(scrambleTrimmedList).isNotEqualTo(questionList);
    }

    @Test
    void testSaveQuestion() {
        Question savedQuestion = questionService.saveQuestion(question);

        assertThat(savedQuestion).isEqualTo(question);
    }

    @Test
    void testGetQuestionById() {
        questionService.saveQuestion(question);

        Question foundQuestion = questionService.getQuestionById(question.get_id());

        assertThat(foundQuestion).isEqualTo(question);
    }

    @Test
    void getQuestionsByCategory() {
        questionService.saveQuestion(question);

        List<Question> foundQuestions = questionService.getQuestionsByCategory("SCIENCE");

        assertThat(foundQuestions).isNotNull().isNotEmpty();
        assertThat(foundQuestions.get(0)).isEqualTo(question);
    }

    @Test
    void testUpdateQuestion() {
        questionService.saveQuestion(question);

        question.setQuestionText("What homonym refers to both a blunt wooden implement used for striking, and a small flying mammal?");
        Document updatedQuestionDoc = QuestionUtils.questionToDocument(question);
        when(collection.findOneAndReplace(any(), any())).thenReturn(updatedQuestionDoc);

        Question updatedQuestion = questionService.updateQuestion(question);

        assertThat(updatedQuestion.get_id()).isEqualTo(question.get_id());
        assertThat(updatedQuestion.getQuestionText()).isEqualTo("What homonym refers to both a blunt wooden implement used for striking, and a small flying mammal?");
        assertThat(updatedQuestion.getAnswerText()).isEqualTo(question.getAnswerText());
        assertThat(updatedQuestion.getCategory()).isEqualTo(question.getCategory());
    }

    @Test
    void testDeleteQuestion() {
        questionService.saveQuestion(question);

        Question deletedQuestion = questionService.deleteQuestion(question.get_id());

        assertThat(deletedQuestion).isEqualTo(question);
    }
}
