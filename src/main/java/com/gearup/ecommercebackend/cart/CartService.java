package com.gearup.ecommercebackend.cart;

import com.gearup.ecommercebackend.product.Inventory;
import com.gearup.ecommercebackend.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addToCart(CartItem cartItem) {
        try {
            Optional<Inventory> inventory = productRepository.findInventoryBySku(cartItem.getSku());
            if(inventory.isEmpty() || inventory.get().getInventoryCount() <= 0) {
                throw new IllegalStateException("Item is out of stock");
            }
            Optional<CartItem> cartItemBySku = cartItemRepository.findCartItemBySku(cartItem.getSku());
            if (cartItemBySku.isPresent()) {
                cartItemBySku.get().setQuantity(cartItemBySku.get().getQuantity() + cartItem.getQuantity());
            } else {
                cartItemRepository.save(cartItem);
            }
        } catch (Error error) {
            throw new IllegalStateException("Issue occurred while adding item to cart");
        }
    }

    public void removeItemFromCart(Long cartId) {
        cartItemRepository.deleteById(cartId);
    }

    public Optional<CartItem> getCartItemById(Long itemId) {
        return cartItemRepository.findById(itemId);
    }

    public Optional<CartItem> getCartItemBySku(String sku) {
        return cartItemRepository.findCartItemBySku(sku);
    }

    public ArrayList<CartItem> fetchCart(String userId) {
        return cartItemRepository.findAllCartItemByUserId(userId);
    }

    @Transactional
    public void removeAllItems(String userId) {
       try{
           cartItemRepository.deleteAllCartItemByUserId(userId);
       }catch (Error err) {
           throw new IllegalStateException("Error while clearing cart", err);
       }
    }


}
