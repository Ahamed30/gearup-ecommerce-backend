package com.gearup.ecommercebackend.utils;

public class InventoryUtils {
    private InventoryUtils() {

    }

    public static String getSku(String productId, String size) {
        return productId + ":" + size;
    }
}
