package com.gearup.ecommercebackend.cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class Cart {
    private String userId;
    private ArrayList<CartItem> items;
    private int itemsCount;
    private BigDecimal cartSubTotal;

    public Cart() {
    }

    public Cart(String userId) {
        this.userId = userId;
    }

    public Cart(String userId, ArrayList<CartItem> items,
                int itemsCount, BigDecimal cartSubTotal) {
        this.userId = userId;
        this.items = items;
        this.itemsCount = itemsCount;
        this.cartSubTotal = cartSubTotal;
    }
}
