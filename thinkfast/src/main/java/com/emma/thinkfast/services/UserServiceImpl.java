package com.emma.thinkfast.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emma.thinkfast.models.User;
import com.emma.thinkfast.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User user) {
        user.setEncPw(passwordEncoder.encode(user.getEncPw()));
        return userRepository.save(user);
    }

    //takes login info from User body
    public User loginUser(User user) {
        String enteredUserName = user.getUsername();
        try {
            User foundUser = userRepository.findByUsername(enteredUserName).get();
            String enteredPw = passwordEncoder.encode(user.getEncPw());
            String userPw = foundUser.getEncPw();
            if (enteredPw.equals(userPw)) {
                Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUserName(), requestDTO.getPw()));
                SecurityContextHolder.getContext().setAuthentication(auth);
                String jwt = tokenProvider.generateToken(auth);

                return ResponseEntity.ok(new JwtResponse(jwt));
            } else {
                //user is not authorized
            }
        } catch (NoSuchElementException nsee) {

        } catch (Exception e) {

        }
    }
}
