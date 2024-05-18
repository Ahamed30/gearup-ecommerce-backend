package com.gearup.ecommercebackend.order;


import com.gearup.ecommercebackend.cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table
public class OrderConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", allocationSize = 1)
    private Long id;
    private String userId;
    private List<Long> cartItemIds;

    public OrderConfirmation() {
    }

    public OrderConfirmation(String userId) {
        this.userId = userId;
    }

    public OrderConfirmation(Long id, String userId, List<Long> cartItemIds) {
        this.id = id;
        this.userId = userId;
        this.cartItemIds = cartItemIds;
    }

    public OrderConfirmation(String userId, List<Long> cartItemIds) {
        this.userId = userId;
        this.cartItemIds = cartItemIds;
    }



}
