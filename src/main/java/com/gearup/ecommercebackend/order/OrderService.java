package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PlacedCartItems placedCartItems;

    @Autowired
    public OrderService(OrderRepository orderRepository, PlacedCartItems placedCartItems) {
        this.orderRepository = orderRepository;
        this.placedCartItems = placedCartItems;
    }

    public Long placeOrder(OrderConfirmation orderConfirmation) {
        OrderConfirmation savedOrder = orderRepository.save(orderConfirmation);
        return savedOrder.getId();
    }

    public void addToPlacedCart(List<CartItem> cartItems) {
        try{
            placedCartItems.saveAll(cartItems);
        }catch (Error err) {
            throw new IllegalStateException("Error while saving to placed Order list", err);
        }
    }
}
