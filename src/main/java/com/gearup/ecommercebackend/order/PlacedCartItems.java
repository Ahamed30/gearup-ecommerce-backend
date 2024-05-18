package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedCartItems extends JpaRepository<CartItem, Long> {
}
