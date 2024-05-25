package com.gearup.ecommercebackend.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table
public class PlacedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placed_items_seq")
    @SequenceGenerator(name = "placed_items_seq", sequenceName = "placed_items_seq", allocationSize = 1)
    private Long id;
    private String userId;
    private String productId;
    private int quantity;
    private String heroImageId;
    private String heroImageURL;
    private String productName;
    private BigDecimal price;
    private BigDecimal salePrice;
    private String size;

    public PlacedItems() {
    }

    public PlacedItems(String userId, String productId, int quantity, String heroImageId,
                       String heroImageURL, String productName, BigDecimal price,
                       BigDecimal salePrice, String size) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.heroImageId = heroImageId;
        this.heroImageURL = heroImageURL;
        this.productName = productName;
        this.price = price;
        this.salePrice = salePrice;
        this.size = size;
    }

    public PlacedItems(Long id, String userId, String productId, int quantity,
                       String heroImageId, String heroImageURL, String productName,
                       BigDecimal price, BigDecimal salePrice, String size) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.heroImageId = heroImageId;
        this.heroImageURL = heroImageURL;
        this.productName = productName;
        this.price = price;
        this.salePrice = salePrice;
        this.size = size;
    }
}
