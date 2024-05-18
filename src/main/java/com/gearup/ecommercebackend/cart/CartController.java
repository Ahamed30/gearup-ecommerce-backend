package com.gearup.ecommercebackend.cart;

import com.gearup.ecommercebackend.utils.InventoryUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public Cart modifyToCartFormat(ArrayList<CartItem> cartItems, String userId) {
        Cart cart = new Cart(userId);
        cart.setItems(cartItems);
        cart.setItemsCount(cartItems.size());
        BigDecimal subTotal = new BigDecimal(0);
        for(CartItem item: cartItems) {
            if(item.getSalePrice() != null) {
                subTotal = subTotal.add(item.getSalePrice());
            }else {
                subTotal = subTotal.add((item.getPrice()));
            }
        }
        cart.setCartSubTotal(subTotal);
        return cart;
    }

    @PostMapping(path= "/addToCart")
    public Cart addToCart(@RequestBody CartItem cartItem) {
        try{
            //Need to check inventory before adding
            //check quantity increase should not add new product
            cartItem.setSku(InventoryUtils.getSku(cartItem.getProductId(), cartItem.getSize()));
            cartService.addToCart(cartItem);
            ArrayList<CartItem> cartItems = cartService.fetchCart(cartItem.getUserId());
            return modifyToCartFormat(cartItems, cartItem.getUserId());
        }catch(Error error) {
            throw new IllegalStateException("Unable to add product to cart", error);
        }
    }

    @PostMapping(path= "/addAllItemsToCart")
    public Cart addAllItemToCart(@RequestBody CartItem[] allCartItems) {
        for(CartItem cartItem: allCartItems) {
            cartItem.setSku(InventoryUtils.getSku(cartItem.getProductId(), cartItem.getSize()));
            cartService.addToCart(cartItem);
        }
        ArrayList<CartItem> cartItems = cartService.fetchCart(allCartItems[0].getUserId());
        return modifyToCartFormat(cartItems, allCartItems[0].getUserId());
    }

    @DeleteMapping(path= "/removeItemFromCart")
    public Cart removeItemFromCart(@RequestParam Long itemId) {
        try{
            //Need to check inventory before deleting
            //increase quantity of inventory
            Optional<CartItem> cartItem = cartService.getCartItemById(itemId);
            if(cartItem.isEmpty()) throw new Error("Item not present");
            cartService.removeItemFromCart(itemId);
            ArrayList<CartItem> cartItems = cartService.fetchCart(cartItem.get().getUserId());
            return modifyToCartFormat(cartItems, cartItem.get().getUserId());
        }catch(Error error) {
            throw new IllegalStateException("Unable to delete product to cart", error);
        }
    }

    @GetMapping
    public Cart fetchCart(@RequestParam String userId) {
        ArrayList<CartItem> cartItems = cartService.fetchCart(userId);
        return modifyToCartFormat(cartItems, userId);
    }
}
