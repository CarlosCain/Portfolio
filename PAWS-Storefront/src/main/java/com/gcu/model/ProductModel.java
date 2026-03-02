package com.gcu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model representing a product.
 */
public class ProductModel {

    private Long id;

    @NotNull(message = "Order number is required")
    @Size(min = 1, max = 32, message = "Order number must be between 1 and 32 characters")
    private String orderNo;

    @NotNull(message = "Product name is required")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    private String productName;

    @NotNull(message = "Product company is required")
    @Size(min = 1, max = 100, message = "Product company must be between 1 and 100 characters")
    private String productCompany;

    @Min(value = 0, message = "Price must be a positive number")
    private float price;

    @Min(value = 0, message = "Quantity must be a positive number")
    private int quantity;

    @Size(max = 500, message = "Description is too long")
    private String description;

 // Default constructor
    /**
     * Default constructor for ProductModel.
     */
    public ProductModel() {}

    // All-arguments constructor
    /**
     * Constructor for ProductModel with all fields.
     *
     * @param id the ID of the product
     * @param orderNo the order number of the product
     * @param productName the name of the product
     * @param productCompany the company of the product
     * @param price the price of the product
     * @param quantity the quantity of the product
     * @param description the description of the product
     */
    public ProductModel(Long id, String orderNo, String productName, String productCompany, float price, int quantity, String description) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.productCompany = productCompany;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters and Setters

    /**
     * Gets the ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the order number.
     *
     * @return the order number
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Sets the order number.
     *
     * @param orderNo the order number to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName the product name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the product company.
     *
     * @return the product company
     */
    public String getProductCompany() {
        return productCompany;
    }

    /**
     * Sets the product company.
     *
     * @param productCompany the product company to set
     */
    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", productName='" + productName + '\'' +
                ", productCompany='" + productCompany + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}