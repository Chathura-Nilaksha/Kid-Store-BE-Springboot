package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_shipping_address_data")
public class OrderShippingAddressDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private Integer customerCodeFromDB; // customer identifying code
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String district;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OneToOne(mappedBy = "orderShippingAddressDataEntity")
    @OneToOne
    @JoinColumn(name = "fk_order_code", nullable = false)
    private CartItemsListEntity cartItemsListEntity;
}
