package org.example.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer customerCode;

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
