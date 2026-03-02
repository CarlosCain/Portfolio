package com.gcu.business;

import com.gcu.model.User;
import com.gcu.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service for handling user-related operations.
 */
@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService.
     *
     * @param userDao the UserDao to use for database operations
     */
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registers a new user.
     *
     * @param user the user to register
     * @return true if the user is registered successfully, false otherwise
     */
    @Transactional
    public boolean registerUser(User user) {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean userCreated = userDao.create(user);
        if (userCreated) {
            userDao.addAuthority(user.getUsername(), "ROLE_USER");
        }
        return userCreated;
    }

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<User> loadUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}