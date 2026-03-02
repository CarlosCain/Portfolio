package com.gcu.business;

import com.gcu.model.CartItem;
import com.gcu.repository.CartItemDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemDao cartItemDao;

    public CartService(CartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    public List<CartItem> getCartByUserId(Integer userId) {
        return cartItemDao.findByUserId(userId);
    }

    public boolean addToCart(CartItem item) {
        return cartItemDao.create(item);
    }

    public boolean updateCartItem(CartItem item) {
        return cartItemDao.update(item);
    }

    public boolean removeFromCart(CartItem item) {
        return cartItemDao.delete(item);
    }

    public double calculateCartTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * getProductPrice(item.getProductId()))
                .sum();
    }

    private double getProductPrice(int productId) {
        // TODO: Replace with actual ProductService or DAO
        return 0.0;
    }
}