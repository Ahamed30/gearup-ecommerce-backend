package com.gearup.ecommercebackend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<Inventory> getInventory(String productId) {
        return productRepository.findAllInventoryByProductId(productId);
    }

    @Transactional
    public void updateInventory(Inventory inventory) {
        try {
            Optional<Inventory> inventoryWithCount = productRepository.findInventoryBySku(inventory.getSku());
            if (inventoryWithCount.isPresent()) {
                inventoryWithCount.get().setInventoryCount(inventory.getInventoryCount());
            } else {
                productRepository.save(inventory);
            }
        } catch (Error error) {
            throw new IllegalStateException("Issue occurred while adding inventory");
        }
    }
}
