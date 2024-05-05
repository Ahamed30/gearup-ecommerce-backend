package com.gearup.ecommercebackend.checkout;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Checkout {
    @Id
    @SequenceGenerator(
            name="id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "id"
    )
    private Long id;
    @Column(unique = true)
    private String userId;
    private String contactEmailId;
    private String firstName;
    private String lastName;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String deliveryType;
    private String deliveryFee;

    public Checkout() {
    }

    public Checkout(String userId, String contactEmailId, String firstName,
                    String lastName, String streetName, String city,
                    String state, String country, String postalCode,
                    String phoneNumber, String deliveryType, String deliveryFee) {
        this.userId = userId;
        this.contactEmailId = contactEmailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.deliveryType = deliveryType;
        this.deliveryFee = deliveryFee;
    }
}
