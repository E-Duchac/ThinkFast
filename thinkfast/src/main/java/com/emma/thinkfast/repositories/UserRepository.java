package com.emma.thinkfast.repositories;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emma.thinkfast.models.User;
import com.emma.thinkfast.utils.UserUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

@Component
public class UserRepository {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;
    
    @Autowired
    public UserRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getDatabase("ThinkFast").getCollection("user");
    }

    public User save(User user) {
        Document userDoc = UserUtils.userToDocument(user);
        collection.insertOne(userDoc);
        return user;
    }

    public Optional<User> findById(String userId) {
        Document document = collection.find(new Document("_id", userId)).first();
        return Optional.of(UserUtils.documentToUser(document));
    }

    public Optional<User> findByEmail(String email) {
        Document document = collection.find(new Document("email", email)).first();
        return Optional.of(UserUtils.documentToUser(document));
    }

    public Optional<User> findByUsername(String username) {
        Document document = collection.find(new Document("username", username)).first();
        return Optional.of(UserUtils.documentToUser(document));
    }

    public Optional<User> updateById(User user) {
        Document document = collection.findOneAndReplace(
            collection.find(new Document("_id", user.get_id())).first(),
            UserUtils.userToDocument(user));
        return Optional.of(UserUtils.documentToUser(document));
    }

    public Optional<User> deleteById(String userId) {
        Document document = collection.findOneAndDelete(new Document("_id", userId));
        return Optional.of(UserUtils.documentToUser(document));
    }
}
