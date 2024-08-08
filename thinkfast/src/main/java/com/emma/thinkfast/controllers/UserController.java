package com.emma.thinkfast.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.dtos.JwtResponse;
import com.emma.thinkfast.dtos.LoginRequest;
import com.emma.thinkfast.models.User;
import com.emma.thinkfast.repositories.UserRepository;
import com.emma.thinkfast.services.AuthServiceImpl;
import com.emma.thinkfast.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") //Remember to reconfigure!
public class UserController {
    private final UserRepository userRepo;
    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    public UserController(UserRepository userRepo, UserServiceImpl userService, AuthServiceImpl authService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
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
        } catch (Exception e) {
            logger.log(Level.WARNING, "Registry failed; exception encountered: {0}", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Registry failed; exception encountered: " + e.getStackTrace());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest requestDTO) {
        try {
            String enteredPassword = requestDTO.getPassword();
            String userPassword = userService.loadUserByUsername(requestDTO.getUsername()).getPassword();
            if (passwordEncoder.matches(enteredPassword, userPassword)) {
                String token = authService.authenticateUser(requestDTO.getUsername(), requestDTO.getPassword());
                logger.log(Level.INFO, "User {0} logged in", requestDTO.getUsername());
                return ResponseEntity.ok(new JwtResponse(token));
            } else {
                logger.log(Level.INFO, "User {0} used incorrect password", requestDTO.getUsername());
                return ResponseEntity.status(401).body("Invalid password.");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "User {0} failed to log in", e.getStackTrace());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // @GetMapping("/getUserById")
    // @GetMapping("/getUserByEmail")
    // @GetMapping("/getUserByUsername"), etc.

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return ResponseEntity.ok("Stubbed updateUser");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String userId) {
        return ResponseEntity.ok("Stubbed deleteUser");
    }
}
