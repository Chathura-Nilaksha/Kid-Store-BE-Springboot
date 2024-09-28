package org.example.dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomerDto {

    private Integer customerCode;
        // when registering this does nt receive any input from FE.
        // But when update this received the customer's customer code to this
    private String firstName;
    private String lastName;
    private String phoneNumber1;
    private String phoneNumber2;   // op
    private String whatsappNumber; // op
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2; // op
    private String city;
    private String district;
    private Integer yearOfBirth;
    private String gender;
}
