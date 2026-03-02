package com.gcu.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderDetail;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object for handling order detail-related database operations.
 */
@Repository
public class OrderDetailDao implements DataAccessInterface<OrderDetail> {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for OrderDetailDao.
     *
     * @param jdbcTemplate the JdbcTemplate to use for database operations
     */
    public OrderDetailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Finds all order details.
     *
     * @return a list of all order details
     */
    @Override
    public List<OrderDetail> findAll() {
        String sql = "SELECT * FROM order_details";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class));
    }

    /**
     * Finds an order detail by its ID.
     *
     * @param id the ID of the order detail
     * @return the order detail with the specified ID
     */
    @Override
    public OrderDetail findById(int id) {
        String sql = "SELECT * FROM order_details WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(OrderDetail.class));
    }

    /**
     * Finds all order details for a specific order.
     *
     * @param orderId the ID of the order
     * @return a list of order details for the order
     */
    public List<OrderDetail> findByOrderId(int orderId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, new BeanPropertyRowMapper<>(OrderDetail.class));
    }

    /**
     * Creates a new order detail.
     *
     * @param detail the order detail to create
     * @return true if the detail is created successfully, false otherwise
     */
    @Override
    public boolean create(OrderDetail detail) {
        String sql = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                detail.getOrderId(),
                detail.getProductId(),
                detail.getQuantity(),
                detail.getPrice()) > 0;
    }

    /**
     * Updates an existing order detail.
     *
     * @param detail the order detail to update
     * @return true if the detail is updated successfully, false otherwise
     */
    @Override
    public boolean update(OrderDetail detail) {
        String sql = "UPDATE order_details SET quantity = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                detail.getQuantity(),
                detail.getPrice(),
                detail.getId()) > 0;
    }

    /**
     * Deletes an order detail.
     *
     * @param detail the order detail to delete
     * @return true if the detail is deleted successfully, false otherwise
     */
    @Override
    public boolean delete(OrderDetail detail) {
        String sql = "DELETE FROM order_details WHERE id = ?";
        return jdbcTemplate.update(sql, detail.getId()) > 0;
    }
}