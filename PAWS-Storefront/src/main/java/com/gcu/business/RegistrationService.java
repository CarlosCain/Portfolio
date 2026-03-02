package com.gcu.business;

import org.springframework.stereotype.Service;

/**
 * Service for handling user registration.
 */
@Service
public class RegistrationService {

    /**
     * Registers a new user.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return a message indicating the registration status
     */
    public String register(String username, String password) {
        return "User registered successfully";
    }
}