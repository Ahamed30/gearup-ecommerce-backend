package com.gearup.ecommercebackend.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findInventoryBySku(String sku);
    ArrayList<Inventory> findAllInventoryByProductId(String productId);
}
