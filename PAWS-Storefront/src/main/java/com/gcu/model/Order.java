package com.gcu.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

/**
 * Model representing a completed order.
 */
public class Order {

    private int id;

    @NotNull(message = "User ID is required")
    private int userId;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    @NotNull(message = "Total amount is required")
    private double total;

    /**
     * Default constructor for Order.
     */
    public Order() {}

    /**
     * Constructor for Order with all fields.
     *
     * @param id the order ID
     * @param userId the ID of the user who placed the order
     * @param orderDate the date and time of the order
     * @param total the total cost of the order
     */
    public Order(int id, int userId, LocalDateTime orderDate, double total) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.total = total;
    }

    // Getters and Setters

    /**
     * Gets the order ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     *
     * @param orderDate the order date to set
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the total cost of the order.
     *
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the total cost of the order.
     *
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", total=" + total +
                '}';
    }
}