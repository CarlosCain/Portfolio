package com.gcu.business;

import com.gcu.model.CartItem;
import com.gcu.model.Order;
import com.gcu.model.OrderDetail;
import com.gcu.repository.OrderDao;
import com.gcu.repository.OrderDetailDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for handling order-related business logic.
 */
@Service
public class OrderService {

    private final OrderDao orderDao;
    private final OrderDetailDao orderDetailDao;
    private final ProductService productService;

    /**
     * Constructor for OrderService.
     *
     * @param orderDao the DAO used for order persistence
     * @param orderDetailDao the DAO used for order detail persistence
     * @param productService the service used to fetch product information
     */
    public OrderService(OrderDao orderDao, OrderDetailDao orderDetailDao, ProductService productService) {
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
        this.productService = productService;
    }

    /**
     * Places an order based on the user's cart items.
     *
     * @param userId the ID of the user
     * @param cartItems the list of cart items
     * @return true if the order is placed successfully
     */
    public boolean placeOrder(int userId, List<CartItem> cartItems) {

        // ⭐ Calculate total using real product prices
        double total = calculateCartTotal(cartItems);

        // ⭐ Create the order object
        Order order = new Order(0, userId, LocalDateTime.now(), total);

        // ⭐ Save the order
        boolean orderCreated = orderDao.create(order);
        if (!orderCreated) {
            return false;
        }

        // ⭐ Retrieve the latest order ID for this user
        List<Order> userOrders = orderDao.findByUserId(userId);
        int orderId = userOrders.get(userOrders.size() - 1).getId();

        // ⭐ Build order details list
        List<OrderDetail> details = new ArrayList<>();
        for (CartItem item : cartItems) {
            double price = getProductPrice(item.getProductId());

            OrderDetail detail = new OrderDetail(
                    0,
                    orderId,
                    item.getProductId(),
                    item.getQuantity(),
                    price
            );

            details.add(detail);
        }

        // ⭐ Save each order detail
        for (OrderDetail detail : details) {
            orderDetailDao.create(detail);
        }

        return true;
    }

    /**
     * Retrieves all orders for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of orders
     */
    public List<Order> getOrderHistory(int userId) {
        return orderDao.findByUserId(userId);
    }

    /**
     * Calculates the total cost of all items in the cart.
     *
     * @param cartItems the list of cart items
     * @return the total cost
     */
    private double calculateCartTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * getProductPrice(item.getProductId()))
                .sum();
    }

    /**
     * Fetches the real product price using ProductService.
     *
     * @param productId the product ID
     * @return the product price
     */
    private double getProductPrice(int productId) {
        return productService.getProductById(productId).getPrice();
    }
}