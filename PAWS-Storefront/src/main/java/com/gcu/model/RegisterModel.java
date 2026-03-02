package com.gcu.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model representing the registration form data.
 */
public class RegisterModel {

    @NotNull(message = "Enter name")
    @Size(max = 32, message = "First name is too long")
    private String firstName;

    @NotNull(message = "Enter last name")
    @Size(max = 32, message = "Last name is too long")
    private String lastName;

    private String username;

    @NotNull(message = "Enter email")
    @Size(max = 32, message = "Email is too long")
    private String email;

    @NotNull(message = "Enter password")
    @Size(min = 2, max = 32, message = "Password must be between 2 and 32 characters")
    private String password;

    @NotNull(message = "Enter password")
    @Size(min = 2, max = 32, message = "Password must be between 2 and 32 characters")
    private String confirmPassword;

    @NotNull(message = "Enter address")
    @Size(min = 1, max = 64, message = "Address must be between 1 and 64 characters")
    private String address;

    @NotNull(message = "Enter phone")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 characters")
    private String phone;

    // Getters and Setters

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the confirm password.
     *
     * @return the confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password.
     *
     * @param confirmPassword the confirm password to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}