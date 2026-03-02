package com.gcu.contoller;

import com.gcu.business.ProductService;
import com.gcu.controller.ProductController;
import com.gcu.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(
                new ProductModel(),
                new ProductModel()
        ));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("title"));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testShowCreateProductForm() throws Exception {
        mockMvc.perform(get("/products/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("productModel"))
                .andExpect(model().attributeExists("title"));
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductModel product = new ProductModel();

        mockMvc.perform(post("/products/create")
                .flashAttr("productModel", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));

        verify(productService, times(1)).createProduct(any(ProductModel.class));
    }

    @Test
    void testShowUpdateProductForm() throws Exception {
        ProductModel product = new ProductModel();
        when(productService.getProductById(1)).thenReturn(product);

        mockMvc.perform(get("/products/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateProduct"))
                .andExpect(model().attributeExists("productModel"))
                .andExpect(model().attributeExists("title"));

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductModel product = new ProductModel();

        mockMvc.perform(post("/products/update")
                .flashAttr("productModel", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));

        verify(productService, times(1)).updateProduct(any(ProductModel.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        ProductModel product = new ProductModel();
        when(productService.getProductById(1)).thenReturn(product);

        mockMvc.perform(get("/products/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));

        verify(productService, times(1)).deleteProduct(product);
    }
}
