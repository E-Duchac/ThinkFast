package com.emma.thinkfast.utils;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.emma.thinkfast.enums.Role;
import com.emma.thinkfast.models.User;

public class UserUtils {
    public static Document UserToDocument(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", user.get_id());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("userName", user.getLastName());
        map.put("encPw", user.getEncPw());
        map.put("pwSalt", user.getPwSalt());
        map.put("email", user.getEmail());
        map.put("role", user.getRole());
        return new Document(map);
    }

    public static User DocumentToUser(Document document) {
        User user = new User();
        user.set_id(document.getString("_id"));
        user.setFirstName(document.getString("firstName"));
        user.setLastName(document.getString("lastName"));
        user.setUsername(document.getString("userName"));
        user.setEncPw(document.getString("encPw"));
        user.setPwSalt(document.getString("pwSalt"));
        user.setEmail(document.getString("email"));
        user.setRole(Enum.valueOf(Role.class, document.getString("role")));
        return user;
    }
}
