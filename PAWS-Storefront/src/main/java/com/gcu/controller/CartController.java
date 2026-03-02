package com.gcu.controller;

import com.gcu.business.CartService;
import com.gcu.business.ProductService;
import com.gcu.model.CartItem;
import com.gcu.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String viewCart(Model model) {
        // Guest cart
        List<CartItem> cartItems = cartService.getCartByUserId(null);
        double total = cartService.calculateCartTotal(cartItems);

        // Load products so the top table renders
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int productId, @RequestParam int quantity) {
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setUserId(null); // Guest cart
        cartService.addToCart(item);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItem(@ModelAttribute CartItem item) {
        item.setUserId(null);
        cartService.updateCartItem(item);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("id") int id) {
        CartItem item = new CartItem();
        item.setId(id);
        cartService.removeFromCart(item);
        return "redirect:/cart";
    }
}