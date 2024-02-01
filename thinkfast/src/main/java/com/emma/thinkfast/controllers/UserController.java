package com.emma.thinkfast.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emma.thinkfast.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    //Create
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("stubbed createUser");
    }

    //Read
    @GetMapping("/getAllUsers")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("stubbed getAllUsers");
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<String> getUser(@PathVariable String userId) {
        return ResponseEntity.ok("stubbed getUser");
    }

    //Update
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String userId) {
        return ResponseEntity.ok("stubbed updateUser");
    }

    //Delete
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok("stubbed deleteUser");
    }
}
