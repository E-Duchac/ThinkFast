package com.emma.thinkfast.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

class UserServiceTests {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepo;

    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepo, passwordEncoder);

        String rawPassword = "electric-spider";
        String encodedPassword = "encoded-electric-spider";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        user = new User();
        user.set_id("ignore-me-test-id");
        user.setFirstName("Miles");
        user.setLastName("Morales");
        user.setUsername("whatsUpDanger");
        user.setPassword(rawPassword);
        user.setEmail("whatsUpDanger@email.com");
        user.setRole(Role.ROLE_STUDENT);
        List<Category> faveCategoriesList = new ArrayList<Category>();
        faveCategoriesList.add(Category.SCIENCE);
        user.setFaveCategories(faveCategoriesList);
    }

    @Test
    void testLoadUserByUsername() {
        when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        UserDetails foundUserDetails = userService.loadUserByUsername(user.getUsername());

        assertThat(foundUserDetails).isEqualTo(user);
    }

    @Test
    void registerNewUser() {
        when(userRepo.save(user)).thenReturn(user);

        User registeredUser = userService.registerNewUser(user);

        assertThat(registeredUser).isEqualTo(user);
        assertThat(registeredUser.getPassword()).isEqualTo("encoded-electric-spider");
    }

    @Test
    void testGetUserById() {
        when(userRepo.findById(user.get_id())).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        User foundUser = userService.getUserById(user.get_id());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testGetUserByEmail() {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        User foundUser = userService.getUserByEmail(user.getEmail());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testGetUserByUsername() {
        when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        User foundUser = userService.getUserByUsername(user.getUsername());

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void testUpdateUser() {
        when(userRepo.updateById(user)).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        user.setEmail("spiderVenom@email.com");
        User updatedUser = userService.updateUser(user);

        assertThat(updatedUser).isEqualTo(user);
        assertThat(updatedUser.getEmail()).isEqualTo("spiderVenom@email.com");
    }

    @Test
    void testDeleteUser() {
        when(userRepo.deleteById(user.get_id())).thenReturn(Optional.of(user));
        userService.registerNewUser(user);

        User deletedUser = userService.deleteUser(user.get_id());

        assertThat(deletedUser).isEqualTo(user);
    }
}
