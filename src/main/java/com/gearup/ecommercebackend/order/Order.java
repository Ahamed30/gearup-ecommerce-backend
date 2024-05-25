package com.gearup.ecommercebackend.order;


import com.gearup.ecommercebackend.checkout.Checkout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", allocationSize = 1)
    private Long id;
    private String userId;
    private List<Long> cartItemIds;

    public Order() {
    }

    public Order(String userId) {
        this.userId = userId;
    }

    public Order(Long id, String userId, List<Long> cartItemIds) {
        this.id = id;
        this.userId = userId;
        this.cartItemIds = cartItemIds;
    }

    public Order(String userId, List<Long> cartItemIds) {
        this.userId = userId;
        this.cartItemIds = cartItemIds;
    }
}
