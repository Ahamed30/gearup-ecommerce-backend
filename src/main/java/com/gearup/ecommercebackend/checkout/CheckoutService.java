package com.gearup.ecommercebackend.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public void setCheckout(Checkout checkout) {
        Optional<Checkout> isCheckoutExist = checkoutRepository.findByUserId(checkout.getUserId());
        isCheckoutExist.ifPresent(value -> checkoutRepository.deleteById(value.getId()));
        checkoutRepository.save(checkout);
    }
}
