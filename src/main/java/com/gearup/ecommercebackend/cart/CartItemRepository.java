package com.gearup.ecommercebackend.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    ArrayList<CartItem> findAllCartItemByUserId(String userId);
    Optional<CartItem> findCartItemBySku(String sku);
}
