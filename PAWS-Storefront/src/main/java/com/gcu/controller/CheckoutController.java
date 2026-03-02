package com.gcu.controller;

import com.gcu.model.CartItem;
import com.gcu.model.ProductModel;
import com.gcu.business.CartService;
import com.gcu.business.OrderService;
import com.gcu.business.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final OrderService orderService;
    private final ProductService productService;

    public CheckoutController(CartService cartService, OrderService orderService, ProductService productService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String showCheckout(Model model) {

        // Guest cart (null user)
        List<CartItem> cartItems = cartService.getCartByUserId(null);

        // Build enriched checkout items
        List<CheckoutItem> checkoutItems = new ArrayList<>();
        double total = 0.0;

        for (CartItem item : cartItems) {
            ProductModel product = productService.getProductById(item.getProductId());

            double subtotal = product.getPrice() * item.getQuantity();
            total += subtotal;

            checkoutItems.add(new CheckoutItem(
                    product.getProductName(),
                    product.getProductCompany(),
                    product.getPrice(),
                    item.getQuantity(),
                    subtotal
            ));
        }

        model.addAttribute("checkoutItems", checkoutItems);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/confirm")
    public String confirmPurchase() {

        // Guest cart
        List<CartItem> cartItems = cartService.getCartByUserId(null);

        boolean success = orderService.placeOrder(0, cartItems); // 0 or null for guest

        if (success) {
            return "redirect:/confirmation";
        } else {
            return "redirect:/checkout?error=true";
        }
    }

    // DTO for checkout display
    public static class CheckoutItem {
        public String productName;
        public String companyName;
        public double price;
        public int quantity;
        public double subtotal;

        public CheckoutItem(String productName, String companyName, double price, int quantity, double subtotal) {
            this.productName = productName;
            this.companyName = companyName;
            this.price = price;
            this.quantity = quantity;
            this.subtotal = subtotal;
        }
    }
}