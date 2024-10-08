package com.emma.thinkfast.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Category> faveCategories = user.getFaveCategories();
        List<String> faveCategoriesList = new ArrayList<String>();
        for (Category category : faveCategories) {
            faveCategoriesList.add(category.toString());
        }
        map.put("faveCategories", faveCategoriesList);
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
        List<String> faveCategories = document.getList("faveCategories", String.class);
        List<Category> faveCategoryList = faveCategories.stream()
            .map(Category::valueOf)
            .collect(Collectors.toList());
        user.setFaveCategories(faveCategoryList);
        return user;
    }
}
