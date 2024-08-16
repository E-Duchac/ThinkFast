package com.emma.thinkfast.services;

import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emma.thinkfast.models.User;
import com.emma.thinkfast.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public User registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User getUserById(String userId) {
        try {
            User foundUser = userRepo.findById(userId).get();
            logger.log(Level.INFO, "User with id {0} found", userId);
            return foundUser;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "User not found with id {0}", userId);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve User with id {0}", userId);
            throw e;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            User foundUser = userRepo.findByEmail(email).get();
            logger.log(Level.INFO, "User with email {0} found", email);
            return foundUser;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "User not found with email {0}", email);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve User with email {0}", email);
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            User foundUser = userRepo.findByUsername(username).get();
            logger.log(Level.INFO, "User with username {0} found", username);
            return foundUser;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "User not found with username {0}", username);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve User with username {0}", username);
            throw e;
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            User updateUser = userRepo.updateById(user).get();
            logger.log(Level.INFO, "User with id {0} updated", user.get_id());
            return updateUser;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "User not found with id {0}", user.get_id());
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update User with id {0}", user.get_id());
            throw e;
        }
    }

    @Override
    public User deleteUser(String userId) {
        try {
            User deleteUser = userRepo.deleteById(userId).get();
            logger.log(Level.INFO, "User with id {0} updated", userId);
            return deleteUser;
        } catch (NoSuchElementException nsee) {
            logger.log(Level.WARNING, "User not found with id {0}", userId);
            throw nsee;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete User with id {0}", userId);
            throw e;
        }
    }
}
