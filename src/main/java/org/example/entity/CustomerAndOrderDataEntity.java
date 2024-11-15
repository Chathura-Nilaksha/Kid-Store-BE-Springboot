package org.example.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Embeddable //AS @Embeddable, Now DB is not making a table to this class.
//This class has joined with CartItemsListEntity.This class is sending data
    // directly (as it has as "CustomerAndOrderData" DTO class) when for
    // "PREVIOUS ORDERS DATA REQUEST API".Therefore, @Embeddable is removed.
public class CustomerAndOrderDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String firstName;
    private String firstNameBill;
    private String lastName;
    private String lastNameBill;
    private String phoneNumber;
    private String email;
    private String emailBill;
    private String addressLine1;
    private String addressLine1Bill;
    private String addressLine2;
    private String addressLine2Bill;
    private String country;
    private String countryBill;
    private String district;
    private String districtBill;

    private String ccName;
    private String ccNumber;
    private String ccExpiration;
    private String cccvv;

    @OneToOne
    @JoinColumn(name = "fk_order_code", nullable = false)
    private CartItemsListEntity cartItemsListEntity;
}
