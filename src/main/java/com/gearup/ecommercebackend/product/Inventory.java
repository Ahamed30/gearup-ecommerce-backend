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
    @SequenceGenerator(
            name="id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "id"
    )
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
