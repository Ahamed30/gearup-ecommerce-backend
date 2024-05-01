package com.gearup.ecommercebackend.product;

import com.gearup.ecommercebackend.utils.InventoryUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
//            Inventory p1 = new Inventory("clk29a3wv2o250bpqy9fxnqxq",
//                    InventoryUtils.getSku("clk29a3wv2o250bpqy9fxnqxq", "5"),
//                    100, "5");
//
//            Inventory p2 = new Inventory("clk29a3wv2o250bpqy9fxnqxq",
//                    InventoryUtils.getSku("clk29a3wv2o250bpqy9fxnqxq", "6"),
//                    100, "6");
//
//            repository.saveAll(List.of(p1, p2));
        };
    }
}
