package com.gcu.controller;

import com.gcu.model.Order;
import com.gcu.business.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for displaying a user's order history.
 */
@Controller
@RequestMapping("/orders")
public class OrderHistoryController {

    private final OrderService orderService;

    /**
     * Constructor for OrderHistoryController.
     *
     * @param orderService the service used for retrieving orders
     */
    public OrderHistoryController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Displays the order history for the current user.
     *
     * @param model the model to pass data to the view
     * @return the order history view
     */
    @GetMapping("/history")
    public String viewOrderHistory(Model model) {
        int userId = getCurrentUserId(); // Replace with actual session logic
        List<Order> orders = orderService.getOrderHistory(userId);

        model.addAttribute("orders", orders);
        return "orderHistory";
    }

    /**
     * Placeholder method to retrieve current user ID.
     * Replace with actual session or security context logic.
     *
     * @return the user ID
     */
    private int getCurrentUserId() {
        // TODO: Replace with actual user session logic
        return 1;
    }
}