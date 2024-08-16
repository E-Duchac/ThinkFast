package com.emma.thinkfast.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.dtos.JwtResponse;
import com.emma.thinkfast.dtos.LoginRequest;
import com.emma.thinkfast.models.User;
import com.emma.thinkfast.services.AuthServiceImpl;
import com.emma.thinkfast.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") //Remember to reconfigure!
public class UserController {
    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    public UserController(UserServiceImpl userService, AuthServiceImpl authService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerNewUser(user);
            logger.log(Level.INFO, "New user registered: {0}", user);
            ObjectMapper obMap = new ObjectMapper();
            return ResponseEntity.ok(obMap.writeValueAsString(savedUser));
        } catch (JsonProcessingException jpe) {
            logger.log(Level.SEVERE, "Registry successful, but User not returnable: {0}", jpe.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Registry successful, but User not returnable: " + jpe.getStackTrace());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest requestDTO) {
        String enteredPassword = requestDTO.getPassword();
        String userPassword = userService.loadUserByUsername(requestDTO.getUsername()).getPassword();
        String token;
        try {
            token = authService.authenticateUser(requestDTO.getUsername(), enteredPassword, userPassword);
            logger.log(Level.INFO, "User {0} logged in", requestDTO.getUsername());
            return ResponseEntity.ok(new JwtResponse(token).toString());
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        logger.log(Level.INFO, "User {0} updated: {1}", new Object[]{user.getUsername(), user});
        return ResponseEntity.ok("User updated: " + user);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String userId) {
        User deleteUser = userService.deleteUser(userId);
        return ResponseEntity.ok("User with id " + deleteUser.get_id() + " deleted.");
    }
}
