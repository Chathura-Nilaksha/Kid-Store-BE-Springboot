package org.example.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
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
}
