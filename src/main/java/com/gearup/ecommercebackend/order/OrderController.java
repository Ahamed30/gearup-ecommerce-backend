package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.Cart;
import com.gearup.ecommercebackend.cart.CartController;
import com.gearup.ecommercebackend.cart.CartItem;
import com.gearup.ecommercebackend.cart.CartService;
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

    public OrderController(OrderService orderService, CartService cartService, CartController cartController) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.cartController = cartController;
    }

    @PostMapping(path = "/placeOrder")
    public PlacedOrder placeOrder(@RequestBody OrderConfirmation orderConfirmation) {
        System.out.println("..."+orderConfirmation);
        ArrayList<CartItem> cartItems = cartService.fetchCart(orderConfirmation.getUserId());
        Cart cart = cartController.modifyToCartFormat(cartItems, orderConfirmation.getUserId());
        Long orderId = orderService.placeOrder(orderConfirmation);
        cartService.removeAllItems(orderConfirmation.getUserId());
        orderService.addToPlacedCart(cart.getItems());
        return new PlacedOrder(orderId, orderConfirmation.getUserId(), cart);
    }
}
