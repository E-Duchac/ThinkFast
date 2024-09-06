package com.emma.thinkfast.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.enums.Role;
import com.emma.thinkfast.models.User;

class UserUtilsTest {
    private User user = new User();
    private Document document = new Document();

    @BeforeEach
    void setUp() {
        user.set_id("user-id");
        user.setFirstName("Alfred");
        user.setLastName("Hitchcock");
        user.setUsername("psychoLogicalHorror");
        user.setEmail("ahitchcock@gaslight.com");
        user.setPassword("chocolate-syrup");
        user.setRole(Role.ROLE_STUDENT);
        List<Category> faveCategories = new ArrayList<Category>();
        faveCategories.add(Category.ARTS_HUMANITIES);
        user.setFaveCategories(faveCategories);

        Map<String, Object> map = new HashMap<>();
        map.put("_id", user.get_id());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("role", user.getRole());
        map.put("faveCategories", user.getFaveCategories());
        document.putAll(map);
    }

    @Test
    void testUserToDocument() {
        Document convertedDocument = UserUtils.userToDocument(user);

        assertThat(convertedDocument).isEqualTo(document);
    }

    @Test
    void testDocumentToUser() {
        User convertedUser = UserUtils.documentToUser(document);

        assertThat(convertedUser).isEqualTo(user);
    }
}
