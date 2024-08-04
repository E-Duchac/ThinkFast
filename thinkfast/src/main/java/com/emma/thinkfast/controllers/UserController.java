package com.emma.thinkfast.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.DTOs.LoginRequest;
import com.emma.thinkfast.models.User;
import com.emma.thinkfast.repositories.UserRepository;
import com.emma.thinkfast.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") //Remember to reconfigure!
public class UserController {
    private final UserRepository userRepo;
    private final UserServiceImpl userService;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    public UserController(UserRepository userRepo, UserServiceImpl userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User savedUser = userRepo.save(user);
            logger.log(Level.INFO, "New user registered: {}", user);
            ObjectMapper obMap = new ObjectMapper();
            return ResponseEntity.ok(obMap.writeValueAsString(savedUser));
        } catch (Exception e) {
            logger.log(Level.WARNING, "Registry failed; exception encountered: {}", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Registry failed; exception encountered: " + e.getStackTrace());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest requestDTO) {
        return ResponseEntity.ok("Stubbed loginUser");
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
