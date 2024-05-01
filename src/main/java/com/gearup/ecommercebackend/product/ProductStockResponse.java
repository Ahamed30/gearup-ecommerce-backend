package com.gearup.ecommercebackend.product;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProductStockResponse {
    private Map<String, Integer> size;
    private boolean inStock;

    public ProductStockResponse(Map<String, Integer> size, boolean inStock) {
        this.size = size;
        this.inStock = inStock;
    }
}

