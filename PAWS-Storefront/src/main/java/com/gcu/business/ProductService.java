package com.gcu.business;

import com.gcu.model.ProductModel;
import com.gcu.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for handling product-related operations.
 */
@Service
public class ProductService {

    private final ProductDao productDao;

    /**
     * Constructor for ProductService.
     *
     * @param productDao the ProductDao to use for database operations
     */
    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return true if the product is created successfully, false otherwise
     */
    @Transactional
    public boolean createProduct(ProductModel product) {
        return productDao.create(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public List<ProductModel> getAllProducts() {
        return productDao.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the product with the specified ID
     */
    public ProductModel getProductById(int id) {
        return productDao.findById(id);
    }

    /**
     * Updates an existing product.
     *
     * @param product the product to update
     * @return true if the product is updated successfully, false otherwise
     */
    public boolean updateProduct(ProductModel product) {
        System.out.println("Updating product with ID: " + product.getId());
        return productDao.update(product);
    }

    /**
     * Deletes a product.
     *
     * @param product the product to delete
     * @return true if the product is deleted successfully, false otherwise
     */
    @Transactional
    public boolean deleteProduct(ProductModel product) {
        return productDao.delete(product);
    }
}