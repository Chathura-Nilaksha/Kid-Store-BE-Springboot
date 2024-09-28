package org.example.dto;
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
public class OrderDto {
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

    private String cardType; //insert into entity 3-->card data-with customerCode colum
    //private CartItems [] cartItems;
    private List<CartItems> cartItems; //// object array
                    //entity 4
//object ={ Integer id, String name, Integer quantity, Double price}
    private Double grandTotal; // FE as number//denata meeka use nokara
                                            //multipication valin order item vala
                                            //price hagagamu
                                            //9.17-meeka cartItemListEntity eke colum ekakata demma
}

////////////////////////////////////////////////////////////////////
//NOTE-
//    getter setter ()s from lombok ----
//    if we use Boolean (Wrapper cls)
//        public Boolean getIsSaveShippingDataInDB()
//        public void setIsSaveShippingDataInDB(Boolean isSaveShippingDataInDB)
//    But if use boolean (primetive) --> only generate this
//        public boolean isSaveShippingDataInDB() {
//                return saveShippingDataInDB;
//        }
///////////////////////////////////////////////////////////////////////


//    private Integer orderCode;
//                //maximum nos of items can purchase is 15#
//    private String item1Code;
//    private Double item1UnitPrice;
//    private Double item1SubTotal;
//
//    private String item2Code;
//    private Double item2UnitPrice;
//    private Double item2SubTotal;
//
//    private String item3Code;
//    private Double item3UnitPrice;
//    private Double item3SubTotal;
//
//    private String item4Code;
//    private Double item4UnitPrice;
//    private Double item4SubTotal;
//
//    private String item5Code;
//    private Double item5UnitPrice;
//    private Double item5SubTotal;
//
//    private String item6Code;
//    private Double item6UnitPrice;
//    private Double item6SubTotal;
//
//    private String item7Code;
//    private Double item7UnitPrice;
//    private Double item7SubTotal;
//
//    private String item8Code;
//    private Double item8UnitPrice;
//    private Double item8SubTotal;
//
//    private String item9Code;
//    private Double item9UnitPrice;
//    private Double item9SubTotal;
//
//    private String item10Code;
//    private Double item10unitPrice;
//    private Double item10SubTotal;


