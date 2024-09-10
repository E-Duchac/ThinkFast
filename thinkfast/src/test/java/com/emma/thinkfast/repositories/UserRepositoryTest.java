package com.emma.thinkfast.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import com.emma.thinkfast.enums.Role;
import com.emma.thinkfast.models.User;
import com.emma.thinkfast.utils.UserUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.*;

@DataMongoTest
class UserRepositoryTest {
    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<Document> collection;

    private UserRepository userRepo;
    private FindIterable<Document> findIterable;
    private User user;
    private Document userDoc;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString())).thenReturn(collection);
        userRepo = new UserRepository(mongoClient);

        user = new User();
        user.set_id("ignore-me-test-id");
        user.setFirstName("Peter");
        user.setLastName("Parker");
        user.setUsername("arachnoGuy");
        user.setPassword("spiderman");
        user.setEmail("peterparker@mail.com");
        user.setRole(Enum.valueOf(Role.class, "ROLE_STUDENT"));
        List<Category> faveCategories = new ArrayList<Category>();
        faveCategories.add(Category.SCIENCE);
        user.setFaveCategories(faveCategories);

        userDoc = UserUtils.userToDocument(user);

        findIterable = mock(FindIterable.class);
        when(collection.insertOne(any(Document.class))).thenReturn(mock(InsertOneResult.class));
    }

    @AfterEach
    void tearDown() {
        collection.drop();
    }

    @Test
    void testSave() {
        User savedUser = userRepo.save(user);

        assertThat(savedUser).isNotNull().isEqualTo(user);
    }

    @Test
    void testFindById() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(userDoc);

        User foundUser = userRepo.findById(user.get_id()).get();

        assertThat(foundUser).isNotNull().isEqualTo(user);
    }

    @Test
    void testFindByEmail() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(userDoc);

        User foundUser = userRepo.findByEmail(user.getEmail()).get();

        assertThat(foundUser).isNotNull().isEqualTo(user);
    }

    @Test
    void testFindByUsername() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(userDoc);

        User foundUser = userRepo.findByUsername(user.getUsername()).get();

        assertThat(foundUser).isNotNull().isEqualTo(user);
    }

    @Test
    void testUpdateUser() {
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(userDoc);
        userRepo.save(user);

        user.setUsername("spidervenom");
        when(collection.findOneAndReplace(any(Document.class), any(Document.class)))
            .thenReturn(UserUtils.userToDocument(user));
        User updatedUser = userRepo.updateById(user).get();

        assertThat(updatedUser).isNotNull().isEqualTo(user);
        assertThat(updatedUser.getUsername()).isEqualTo("spidervenom");
    }

    @Test
    void testDeleteUser() {
        when(collection.findOneAndDelete(any(Document.class))).thenReturn(userDoc);
        userRepo.save(user);

        User deletedUser = userRepo.deleteById(user.get_id()).get();

        assertThat(deletedUser).isNotNull().isEqualTo(user);
    }
}
