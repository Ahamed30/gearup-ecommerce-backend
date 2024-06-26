package com.gearup.ecommercebackend.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq")
    @SequenceGenerator(name = "cart_item_seq", sequenceName = "cart_item_seq", allocationSize = 1)
    private Long id;
    private String sku;
    private String userId;
    private String productId;
    private int quantity;
    private String heroImageId;
    private String heroImageURL;
    private String productName;
    private BigDecimal price;
    private BigDecimal salePrice;
    private String size;

    public CartItem() {
    }

    public CartItem(Long id, String sku, String userId, String productId,
                    int quantity, String heroImageId, String heroImageURL,
                    String productName, BigDecimal price, BigDecimal salePrice, String size) {
        this.id = id;
        this.sku = sku;
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

    public CartItem(String sku, String userId, String productId, int quantity, String heroImageId,
                    String heroImageURL, String productName, BigDecimal price, BigDecimal salePrice, String size) {
        this.sku = sku;
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

    public CartItem(String userId, String productId, int quantity,
                    String heroImageId, String heroImageURL, String productName,
                    BigDecimal price, BigDecimal salePrice, String size) {
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
