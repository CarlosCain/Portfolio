package com.gcu.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

/**
 * Data access object for handling user-related database operations.
 */
@Repository
public class UserDao implements DataAccessInterface<User> {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for UserDao.
     *
     * @param jdbcTemplate the JdbcTemplate to use for database operations
     */
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Finds all users.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user
     * @return the user with the specified ID, or null if not found
     */
    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return true if the user is created successfully, false otherwise
     */
    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users (username, password, enabled, F_NAME, L_NAME, EMAIL, ADDRESS, PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.isEnabled(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhone()) > 0;
    }

    /**
     * Updates an existing user.
     *
     * @param user the user to update
     * @return true if the user is updated successfully, false otherwise
     */
    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, enabled = ?, F_NAME = ?, L_NAME = ?, EMAIL = ?, ADDRESS = ?, PHONE = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.isEnabled(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhone(), user.getId()) > 0;
    }

    /**
     * Deletes a user.
     *
     * @param user the user to delete
     * @return true if the user is deleted successfully, false otherwise
     */
    @Override
    public boolean delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, user.getId()) > 0;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * Adds an authority to a user.
     *
     * @param username the username of the user
     * @param authority the authority to add
     */
    public void addAuthority(String username, String authority) {
        String sql = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, authority);
    }
}