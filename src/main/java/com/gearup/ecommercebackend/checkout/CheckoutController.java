package com.gearup.ecommercebackend.checkout;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping(path = "/setCheckout")
    private Checkout setCheckout(@RequestBody Checkout checkout) {
        try{
            checkoutService.setCheckout(checkout);
            return checkout;
        }catch(Error error) {
            throw new IllegalStateException("Unable to save checkout details", error);
        }
    }
}
