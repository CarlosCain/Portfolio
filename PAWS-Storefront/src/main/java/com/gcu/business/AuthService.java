package com.gcu.business;

import com.gcu.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling authentication.
 */
@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    /**
     * Authenticates a user based on their username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String username, String password) {
        return userDao.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}