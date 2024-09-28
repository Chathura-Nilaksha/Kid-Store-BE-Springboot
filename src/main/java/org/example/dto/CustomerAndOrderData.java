package org.example.dto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Component
public class CustomerAndOrderData {
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
