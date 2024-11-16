package org.example.dtoOutgoing;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.CartItems;
import org.example.dto.CustomerAndOrderData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@Embeddable             //This cls is a copy and edited from OrderDto cls.
public class OrderDtoSendingToFE {
    private Integer orderCode;

    @Embedded // CHECK-when API call from Firefox works correctly with this ANO and without this ANO
    private CustomerAndOrderData customerAndOrderData; ////  object
//      String firstName,String firstNameBill,String lastName,String lastNameBill
//      String phoneNumber,String email,String emailBill,String addressLine1
//      String addressLine1Bill,String addressLine2,String addressLine2Bill,
//      String country,String countryBill,String district,String districtBill
//      String ccName,String ccNumber,String ccExpiration,String cccvv
                //above need 3 entity tables
                    //entity 1.shipping address data-with customerCode colum
                    //entity 2.billing address data-with customerCode colum
                    //entity 3.card data-with customerCode colum

    private String cardType; //insert into entity 3-->"card data-with customerCode colum"

    private List<CartItems> cartItems; //// object array
                    //entity 4
//object ={ Integer id, String name, Integer quantity, Double price}
    private Double grandTotal; // FE as number

    private LocalDateTime entityCreatingTime;

}//This cls is a copy and edited from OrderDto cls.

////////////////////////////////////////////////////////////////////
//NOTE:-
//    Getter, setter ()s from lombok ----
//    if we use Boolean (the Wrapper cls <--(Therefore this is better) ) --> getter, setter
//        public Boolean getIsSaveShippingDataInDB()
//        public void setIsSaveShippingDataInDB(Boolean isSaveShippingDataInDB)
//    But if use boolean (the primitive)  --> only generate this
//        public boolean isSaveShippingDataInDB() { return saveShippingDataInDB; }
///////////////////////////////////////////////////////////////////////



