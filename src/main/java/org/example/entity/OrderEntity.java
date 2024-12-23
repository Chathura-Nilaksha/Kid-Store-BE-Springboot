package org.example.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.CartItems;
import org.example.dto.CustomerAndOrderData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

//HOPE TO REMOVE THIS CLASS AFTER SEPARATING ALL INTO SEPARATE ENTITIES..Think now better not to
        // remove.24.11.14.

@MappedSuperclass /*Now not making a table in the DB from this entity.And this fields can be
                    taken to other entity classes.*/
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderCode;
    //@Embedded
    private CustomerAndOrderData customerAndOrderData; //  object
//      String firstName,String firstNameBill,String lastName,String lastNameBill
//      String phoneNumber,String email,String emailBill,String addressLine1
//      String addressLine1Bill,String addressLine2,String addressLine2Bill,
//      String country,String countryBill,String district,String districtBill
//      String ccName,String ccNumber,String ccExpiration,String cccvv

    private Boolean isSaveShippingDataInDB; // check whether simple b or B
    private Boolean isSaveBillingDataInDB; // check whether simple b or B
        // /* When sending data via this object im not inserting a value to above 2 boolean fields.
        //   Then will go with default value. Don't consider it in FE operations. */
    private String cardType;
    //private CartItems[] cartItems; // object array
    //private List<ItemDTO> cartItems;
// { Integer id, String name, Integer quantity, Double price}
    private Double grandTotal; // FE as number
}
