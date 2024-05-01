package com.gearup.ecommercebackend.product;

import com.gearup.ecommercebackend.utils.InventoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path= "/inventory")
    public ProductStockResponse getInventory(@RequestParam String productId) {
        ArrayList<Inventory> inventory = productService.getInventory(productId);
        Map<String, Integer> sizeWithInventory = new HashMap<>();
        ProductStockResponse response = new ProductStockResponse(null, false);
        for(Inventory currInventory: inventory) {
            sizeWithInventory.put(currInventory.getSize(), currInventory.getInventoryCount());
            if(currInventory.getInventoryCount() > 0) {
                response.setInStock(true);
            }
        }
        response.setSize(sizeWithInventory);
        return response;
    }

    @PostMapping(path= "/inventory")
    public String addInventory(@RequestBody Inventory[] inventory) {
        for(Inventory currInventory: inventory) {
            String sku = InventoryUtils.getSku(currInventory.getProductId(), currInventory.getSize());
            currInventory.setSku(sku);
            productService.addInventory(currInventory);
        }
        return "Successfully added inventory added for these products";
    }
}
