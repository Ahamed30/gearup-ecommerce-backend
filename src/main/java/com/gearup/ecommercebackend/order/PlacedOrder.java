package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacedOrder {
    private Long id;
    private String userId;
    private Cart cart;

    public PlacedOrder() {
    }

    public PlacedOrder(Long id, String userId, Cart cart) {
        this.id = id;
        this.userId = userId;
        this.cart = cart;
    }
}
