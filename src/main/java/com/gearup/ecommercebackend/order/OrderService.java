package com.gearup.ecommercebackend.order;

import com.gearup.ecommercebackend.cart.CartItem;
import com.gearup.ecommercebackend.product.Inventory;
import com.gearup.ecommercebackend.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PlacedItemsRepository placedItemsRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PlacedItemsRepository placedItemsRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.placedItemsRepository = placedItemsRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Long placeOrder(Order order, List<CartItem> cartItems) {
        List<Long> cartItemIds = new ArrayList<>();
        for(CartItem cartItem: cartItems) {
            Optional<Inventory> inventory = productRepository.findInventoryBySku(cartItem.getSku());
            if(inventory.isPresent()) {
                inventory.get().setInventoryCount(inventory.get().getInventoryCount() - cartItem.getQuantity());
            }else {
                throw new IllegalStateException(cartItem.getSku()+" Item out stock");
            }
            cartItemIds.add((cartItem.getId()));
        }
        order.setCartItemIds(cartItemIds);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    public void addToPlacedCart(List<CartItem> cartItems) {
        try{
            List<PlacedItems> placedItems = new ArrayList<>();
            for(CartItem cartItem: cartItems) {
                PlacedItems currItem = new PlacedItems(cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity(),
                        cartItem.getHeroImageId(), cartItem.getHeroImageURL(), cartItem.getProductName(),
                        cartItem.getSalePrice(), cartItem.getPrice(), cartItem.getSize());
                placedItems.add(currItem);
            }
            placedItemsRepository.saveAll(placedItems);
        }catch (Error err) {
            throw new IllegalStateException("Error while saving to placed Order list", err);
        }
    }
}
