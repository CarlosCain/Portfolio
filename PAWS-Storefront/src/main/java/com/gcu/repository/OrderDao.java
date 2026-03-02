package com.gcu.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 * Data access object for handling order-related database operations.
 */
@Repository
public class OrderDao implements DataAccessInterface<Order> {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for OrderDao.
     *
     * @param jdbcTemplate the JdbcTemplate to use for database operations
     */
    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Finds all orders.
     *
     * @return a list of all orders
     */
    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    /**
     * Finds an order by its ID.
     *
     * @param id the ID of the order
     * @return the order with the specified ID
     */
    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Order.class));
    }

    /**
     * Finds all orders for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of orders for the user
     */
    public List<Order> findByUserId(int userId) {
        String sql = "SELECT * FROM Orders WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Order.class));
    }

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return true if the order is created successfully, false otherwise
     */
    @Override
    public boolean create(Order order) {
        String sql = "INSERT INTO Orders (user_id, order_date, total) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, order.getUserId(), order.getOrderDate(), order.getTotal()) > 0;
    }

    /**
     * Creates an order and returns the generated ID.
     */
    public long createAndReturnId(Order order) {
        String sql = "INSERT INTO Orders (user_id, order_date, total) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            ps.setDouble(3, order.getTotal());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * Updates an existing order.
     *
     * @param order the order to update
     * @return true if the order is updated successfully, false otherwise
     */
    @Override
    public boolean update(Order order) {
        String sql = "UPDATE Orders SET user_id = ?, order_date = ?, total = ? WHERE id = ?";
        return jdbcTemplate.update(sql, order.getUserId(), order.getOrderDate(), order.getTotal(), order.getId()) > 0;
    }

    /**
     * Deletes an order.
     *
     * @param order the order to delete
     * @return true if the order is deleted successfully, false otherwise
     */
    @Override
    public boolean delete(Order order) {
        String sql = "DELETE FROM Orders WHERE id = ?";
        return jdbcTemplate.update(sql, order.getId()) > 0;
    }
}