package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_billing_address_data")
public class OrderBillingAddressDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private Integer customerCodeFromDB;   // customer identifying code
    private String firstNameBill;
    private String lastNameBill;
    private String emailBill;
    private String addressLine1Bill;
    private String addressLine2Bill;
    private String countryBill;
    private String districtBill;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OneToOne(mappedBy = "orderBillingAddressDataEntity")
    @OneToOne
    @JoinColumn(name = "fk_order_code", nullable = false)
    private CartItemsListEntity cartItemsListEntity;
}
