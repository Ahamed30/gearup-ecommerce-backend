package com.gearup.ecommercebackend.cart;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path= "/addToCart")
    public String addToCart(@RequestBody CartItem cartItem) {
        try{
            cartService.addToCart(cartItem);
            return "Product Added Successfully";
        }catch(Error error) {
            throw new IllegalStateException("Unable to add product to cart", error);
        }
    }

    @GetMapping
    public Cart fetchCart(@RequestParam String userId) {
        Cart cart = new Cart(userId);
        ArrayList<CartItem> cartItems = cartService.fetchCart(userId);
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
}
