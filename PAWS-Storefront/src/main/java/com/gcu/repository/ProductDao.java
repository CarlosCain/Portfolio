package com.gcu.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.ProductModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object for handling product-related database operations.
 */
@Repository
public class ProductDao implements DataAccessInterface<ProductModel> {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for ProductDao.
     *
     * @param jdbcTemplate the JdbcTemplate to use for database operations
     */
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    @Override
    public List<ProductModel> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductModel.class));
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product
     * @return the product with the specified ID
     */
    @Override
    public ProductModel findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(ProductModel.class));
    }

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return true if the product is created successfully, false otherwise
     */
    @Override
    public boolean create(ProductModel product) {
        String sql = "INSERT INTO product (order_no, product_name, product_company, price, quantity, description) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getOrderNo(), product.getProductName(), product.getProductCompany(), product.getPrice(), product.getQuantity(), product.getDescription()) > 0;
    }

    /**
     * Updates an existing product.
     *
     * @param product the product to update
     * @return true if the product is updated successfully, false otherwise
     */
    @Override
    public boolean update(ProductModel product) {
        String sql = "UPDATE product SET order_no = ?, product_name = ?, product_company = ?, price = ?, quantity = ?, description = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getOrderNo(), product.getProductName(), product.getProductCompany(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getId());
        System.out.println("Rows affected: " + rowsAffected);
        return rowsAffected > 0;
    }

    /**
     * Deletes a product.
     *
     * @param product the product to delete
     * @return true if the product is deleted successfully, false otherwise
     */
    @Override
    public boolean delete(ProductModel product) {
        String sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, product.getId()) > 0;
    }
}