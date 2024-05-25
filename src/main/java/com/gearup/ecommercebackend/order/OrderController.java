package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.Cart;
import com.gearup.ecommercebackend.cart.CartController;
import com.gearup.ecommercebackend.cart.CartItem;
import com.gearup.ecommercebackend.cart.CartService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final CartController cartController;

    public OrderController(OrderService orderService, CartService cartService,
                           CartController cartController) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.cartController = cartController;
    }

    @Transactional
    @PostMapping(path = "/placeOrder")
    public PlacedOrder placeOrder(@RequestBody Order order) {
        ArrayList<CartItem> cartItems = cartService.fetchCart(order.getUserId());
        Cart cart = cartController.modifyToCartFormat(cartItems, order.getUserId());
        cartService.removeAllItems(order.getUserId());
        orderService.addToPlacedCart(cart.getItems());
        Long orderId = orderService.placeOrder(order, cartItems);
        return new PlacedOrder(orderId, order.getUserId(), cart);
    }
}
