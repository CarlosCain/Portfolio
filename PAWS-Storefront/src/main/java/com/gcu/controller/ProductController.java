package com.gcu.controller;

import com.gcu.model.ProductModel;
import com.gcu.business.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controller for handling product-related requests.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Displays the form for creating a new product.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("title", "Create Product");
        model.addAttribute("productModel", new ProductModel(null, null, null, null, 0, 0, null));
        return "createProduct";
    }

    /**
     * Handles the submission of the create product form.
     *
     * @param productModel the product model
     * @param bindingResult the binding result
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("productModel") ProductModel productModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Create Product");
            return "createProduct";
        }

        if (productService.createProduct(productModel)) {
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Product creation failed");
            return "createProduct";
        }
    }

    /**
     * Lists all products.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    /**
     * Displays the form for updating a product.
     *
     * @param id the ID of the product to update
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") int id, Model model) {
        ProductModel product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "redirect:/products";
        }
        model.addAttribute("title", "Update Product");
        model.addAttribute("productModel", product);
        return "updateProduct";
    }

    /**
     * Handles the submission of the update product form.
     *
     * @param productModel the product model
     * @param bindingResult the binding result
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("productModel") ProductModel productModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Update Product");
            return "updateProduct";
        }

        if (productService.updateProduct(productModel)) {
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Product update failed");
            return "updateProduct";
        }
    }

    /**
     * Deletes a product.
     *
     * @param id the ID of the product to delete
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        ProductModel product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "redirect:/products";
        }

        if (productService.deleteProduct(product)) {
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Product deletion failed");
            return "redirect:/products";
        }
    }
}