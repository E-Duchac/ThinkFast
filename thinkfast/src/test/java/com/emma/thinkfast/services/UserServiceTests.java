package com.emma.thinkfast.services;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.enums.Role;
import com.emma.thinkfast.models.User;
import com.emma.thinkfast.repositories.UserRepository;
import com.emma.thinkfast.utils.UserUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

class UserServiceTests {
    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mongoDatabase;
    @Mock
    private MongoCollection<Document> collection;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepo;
    private UserServiceImpl userService;
    private FindIterable<Document> findIterable;
    private MongoCursor<Document> cursor;
    private User user;
    private Document userDoc;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mongoClient.getDatabase(anyString())).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection(anyString())).thenReturn(collection);
        userRepo = new UserRepository(mongoClient);
        userService = new UserServiceImpl(userRepo, passwordEncoder);

        user = new User();
        user.set_id("ignore-me-test-id");
        user.setFirstName("Miles");
        user.setLastName("Morales");
        user.setUsername("whatsUpDanger");
        user.setPassword("electric-spider");
        user.setEmail("whatsUpDanger@email.com");
        user.setRole(Role.ROLE_STUDENT);
        List<Category> faveCategoriesList = new ArrayList<Category>();
        faveCategoriesList.add(Category.SCIENCE);
        user.setFaveCategories(faveCategoriesList);

        userDoc = UserUtils.userToDocument(user);

        findIterable = mock(FindIterable.class);
        cursor = mock(MongoCursor.class);
        when(collection.insertOne(any(Document.class))).thenReturn(mock(InsertOneResult.class));
        when(collection.find(any(Document.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(userDoc);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(userDoc);
        when(collection.findOneAndDelete(any(Document.class))).thenReturn(userDoc);
    }

    @AfterEach
    void tearDown() {
        collection.drop();
    }

    @Test
    void testLoadUserByUsername() {
        userService.registerNewUser(user);

        UserDetails foundUserDetails = userService.loadUserByUsername(user.getUsername());

        assertThat(foundUserDetails).isEqualTo(user);
    }

    @Test
    void registerNewUser() {
        User registeredUser = userService.registerNewUser(user);

        assertThat(registeredUser).isEqualTo(user);
    }

    @Test
    void testGetUserById() {
        userService.registerNewUser(user);

        User foundUser = userService.getUserById(user.get_id());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testGetUserByEmail() {
        userService.registerNewUser(user);

        User foundUser = userService.getUserByEmail(user.getEmail());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testGetUserByUsername() {
        userService.registerNewUser(user);

        User foundUser = userService.getUserByUsername(user.getUsername());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testUpdateUser() {
        userService.registerNewUser(user);

        user.setEmail("spiderVenom@email.com");
        User updatedUser = userService.updateUser(user);

        assertThat(updatedUser).isEqualTo(user);
        assertThat(updatedUser.getEmail()).isEqualTo("spiderVenom@email.com");
    }

    @Test
    void testDeleteUser() {
        userService.registerNewUser(user);

        User deletedUser = userService.deleteUser(user.get_id());

        assertThat(deletedUser).isEqualTo(user);
    }
}
