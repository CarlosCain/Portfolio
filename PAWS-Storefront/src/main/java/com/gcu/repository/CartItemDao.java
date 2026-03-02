package com.gcu.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.CartItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemDao implements DataAccessInterface<CartItem> {

    private final JdbcTemplate jdbcTemplate;

    public CartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CartItem> findAll() {
        String sql = "SELECT * FROM cart_items";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class));
    }

    @Override
    public CartItem findById(int id) {
        String sql = "SELECT * FROM cart_items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(CartItem.class));
    }

    public List<CartItem> findByUserId(Integer userId) {
        String sql = (userId == null)
            ? "SELECT * FROM cart_items WHERE user_id IS NULL"
            : "SELECT * FROM cart_items WHERE user_id = ?";
        return (userId == null)
            ? jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class))
            : jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(CartItem.class));
    }

    @Override
    public boolean create(CartItem item) {
        String sql = (item.getUserId() == null)
            ? "INSERT INTO cart_items (product_id, quantity) VALUES (?, ?)"
            : "INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?, ?, ?)";
        return (item.getUserId() == null)
            ? jdbcTemplate.update(sql, item.getProductId(), item.getQuantity()) > 0
            : jdbcTemplate.update(sql, item.getUserId(), item.getProductId(), item.getQuantity()) > 0;
    }

    @Override
    public boolean update(CartItem item) {
        String sql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, item.getQuantity(), item.getId()) > 0;
    }

    @Override
    public boolean delete(CartItem item) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        return jdbcTemplate.update(sql, item.getId()) > 0;
    }
}