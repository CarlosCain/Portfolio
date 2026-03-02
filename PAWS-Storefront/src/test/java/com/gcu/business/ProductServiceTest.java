package com.gcu.business;

import com.gcu.model.ProductModel;
import com.gcu.repository.ProductDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductModel product = new ProductModel();
        when(productDao.create(product)).thenReturn(true);
        assertTrue(productService.createProduct(product));
        verify(productDao, times(1)).create(product);
    }

    @Test
    void testGetAllProducts() {
        List<ProductModel> products = Arrays.asList(
                new ProductModel(),
                new ProductModel()
        );
        when(productDao.findAll()).thenReturn(products);
        List<ProductModel> result = productService.getAllProducts();
        assertEquals(2, result.size());
        verify(productDao, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        ProductModel product = new ProductModel();
        when(productDao.findById(1)).thenReturn(product);
        ProductModel result = productService.getProductById(1);
        assertEquals(product, result);
        verify(productDao, times(1)).findById(1);
    }

    @Test
    void testUpdateProduct() {
        ProductModel product = new ProductModel();
        when(productDao.update(product)).thenReturn(true);
        assertTrue(productService.updateProduct(product));
        verify(productDao, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        ProductModel product = new ProductModel();
        when(productDao.delete(product)).thenReturn(true);
        assertTrue(productService.deleteProduct(product));
        verify(productDao, times(1)).delete(product);
    }
}
