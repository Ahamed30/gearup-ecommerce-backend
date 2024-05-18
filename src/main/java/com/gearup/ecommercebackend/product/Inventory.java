package com.gearup.ecommercebackend.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_item_seq")
    @SequenceGenerator(name = "inventory_item_seq", sequenceName = "inventory_item_seq", allocationSize = 1)
    private Long id;
    private String productId;
    @Column(unique = true)
    private String sku;
    private String size;
    private Integer inventoryCount;

    public Inventory() {
    }

    public Inventory(Long id, String productId, String sku, Integer inventoryCount, String size) {
        this.id = id;
        this.productId = productId;
        this.sku = sku;
        this.inventoryCount = inventoryCount;
        this.size = size;
    }

    public Inventory(String productId, String sku, Integer inventoryCount, String size) {
        this.productId = productId;
        this.sku = sku;
        this.inventoryCount = inventoryCount;
        this.size = size;
    }

}
