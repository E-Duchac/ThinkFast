package com.emma.thinkfast.utils;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.enums.Role;
import com.emma.thinkfast.models.User;

public class UserUtils {
    private UserUtils() {}
    
    public static Document userToDocument(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", user.get_id());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("role", user.getRole());
        map.put("faveCategories", user.getFaveCategories());
        return new Document(map);
    }

    public static User documentToUser(Document document) {
        User user = new User();
        user.set_id(document.getString("_id"));
        user.setFirstName(document.getString("firstName"));
        user.setLastName(document.getString("lastName"));
        user.setUsername(document.getString("username"));
        user.setPassword(document.getString("password"));
        user.setEmail(document.getString("email"));
        user.setRole(Enum.valueOf(Role.class, document.get("role").toString()));
        user.setFaveCategories(document.getList("faveCategories", Category.class));
        return user;
    }
}
