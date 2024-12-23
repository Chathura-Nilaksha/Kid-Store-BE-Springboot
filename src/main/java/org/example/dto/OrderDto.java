package org.example.dto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Embeddable
public class OrderDto{
    private Integer orderCode;

    @Embedded
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

    private Boolean isSaveShippingDataInDB; // use these for send to save to DB
    private Boolean isSaveBillingDataInDB; // use these for send to save to DB

    private String cardType; //insert into entity 3-->"card data-with customerCode colum"
    //private CartItems [] cartItems;
    private List<CartItems> cartItems; //// object array
                    //entity 4
//object ={ Integer id, String name, Integer quantity, Double price}
    private Double grandTotal; // FE as number//denata meeka use nokara multipication
                                            //valin order item vala price hagagamu.
                                            //9.17-meeka cartItemListEntity eke colum ekakata demma
}

////////////////////////////////////////////////////////////////////
//NOTE:-
//    Getter, setter ()s from lombok ----
//    if we use Boolean (the Wrapper cls <--(Therefore this is better) ) --> getter, setter
//        public Boolean getIsSaveShippingDataInDB()
//        public void setIsSaveShippingDataInDB(Boolean isSaveShippingDataInDB)
//    But if use boolean (the primitive)  --> only generate this
//        public boolean isSaveShippingDataInDB() { return saveShippingDataInDB; }
///////////////////////////////////////////////////////////////////////
