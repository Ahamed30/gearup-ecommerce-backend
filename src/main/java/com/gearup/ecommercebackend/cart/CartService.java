package com.gearup.ecommercebackend.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public ArrayList<CartItem> fetchCart(String userId) {
        return cartItemRepository.findAllCartItemByUserId(userId);
    }
}
