package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories
@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

//-----search --- multiple selections: use Tuple or array ?----here----
//" Specified result type [java.lang.Integer]
    //      did not match Query selection type [org.example.entity.CustomerEntity]
    //          - multiple selections: use Tuple or array "

}
//11.16--


//11.15--   --Run the main () at the END -No issues
//--OrderServiceImpl-previousOrders()--
//partly completed inside if

//--MADE--EXEMPTION cls-CustomerAndOrderDataRawNotExistingInDBTable--


//11.14---
//--DELETED -orderRepository -(as orderEntity cls tagged as @MappedSupperClass)
//--MADE- OrderDtoSendingToFE cls, Developed PreviousOrdersData cls


//11.12---
//Resolved---> //(11.10) -OrderServiceImpl issue ()---//released-11.10.check next day by post


//11.10---------
//--CustomerRepository, LoginRepository----
//Inserted the @Queries
//Run the main ()-No issues

//nextD do
//OrderServiceImpl issue ()---//released-11.10.check next day by post


//11.4----------
//SOLVED BELOW ERROR TOTALLY
//SOLVED-"Still pending 9.27 error.check after unit testing the error service layer after above."


//11.3-----------
//same error comes, -even we put Page<Integer> customerCodeFromDB instead of INTEGER
// as return type for below error
//Still pending 9.27 error.check after unit testing the error service layer after above.

//10.31---------------------------
//Still pending 9.27 error.check after unit testing the error service layer after above.
// ABOVE ERROR IS NOT COMING WHEN NO DATA IS IN THE DB TABLE(MEANS NO EMAIL ADDRESS IS IN THE
// DB TABLE, THEN RETURN NULL OBJECT REPLY)
//Now need to check with when email is existing in the db option


//10.30---------------------------------------------
// In CustomerServiceImplTest class --
// complete other test () for failure (catch option)

//Still pending 9.27 error.check after unit testing the error service layer after above



//10.22----------------------------------------------
// Edited the CustomerServiceImpl class for try catch
//Still pending 9.27 error.check after unit testing the error service layer after above

// completed 1 test () in CustomerServiceImplTest class (for success option)
//// NEXT day-need to complete other test () for failure (catch option)
//Still pending 9.27 error.check after unit testing the error service layer after above


//10.19-----------------------------------------------
//Test class for CustomerServiceImplTest-->saveCustomer()-CHECK,TEST-CH & DOC WHETHER OK OR NOT.
//Still pending 9.27 error.check after unit testing the error service layer after above


//10.18-----------------------------------------------
//Applied that EmailNotExisting class to my CustomerNativeRepositoryImpl class
//Prepare Test class for CustomerServiceImplTest-->saveCustomer()-NOT Completed.
//Still pending 9.27 error.check after unit testing the error service layer after above

//10.17------------------------------------------------
//Made EmailNotExisting class-Completed
//Still pending 9.27 error.check after unit testing the error service layer after above


//10.11-------------------------------------------------
//Made Native repository for practising purpose---Completed
//for Class CustomerNativeRepositoryImpl & interface CustomerNativeRepository
//made method--public Optional<CustomerEntity> findCustomerAllDetailsByEmail(String email)-completed
//Still pending 9.27 error.check after unit testing the error service layer after above


//10.6-------------------------------------------------
//Made Native repository for practising purpose-Not Completed
//for Class CustomerNativeRepositoryImpl & interface CustomerNativeRepository
//Still pending 9.27 error.check after unit testing the error service layer after above


//9.27--------------------------------------------------
// Still have that problem
//org.hibernate.query.QueryTypeMismatchException:
// Specified result type [java.lang.Integer] did not match
// Query selection type [org.example.entity.CustomerEntity] -
// multiple selections: use Tuple or array



//9.24---------------------------------------------------

// In CustomerRepositoryTest cls--//
//checked and made one entity in DB.
// Start making it for save a customerDto object to
// check-->PreviousOrdersData previousOrders()--In OrderServiceImpl

//Below ERROR came-when sending the path variable via firefox.--NOT SOLVED YET--//
// Servlet.service() for servlet [dispatcherServlet] in context with path []
// threw exception [Request processing failed: org.springframework.orm.jpa.JpaSystemException:
// Specified result type [java.lang.Integer] did not match Query selection type
// [org.example.entity.CustomerEntity] - multiple selections: use Tuple or array] with
// root cause.----END OF ERROR description.



//9.22----------------------------------------------------

// In OrderServiceImpl--//
//Partly completed-> PreviousOrdersData previousOrders()

// In PreviousOrdersData dtoOutgoing--//
//Made PreviousOrdersData dtoOutgoing for send the order data as an object
//Yet not completed it.

// In CartItemsListEntity--//
// Insert thd time parameter to automatically save the ordering date


//9.19-----------------------------------------------------

// In OrderServiceImpl--//
//NO.4-send card data to 3rd party only to deduct the amount.
//card validation is doing on FE

// In OrderController--//
//Made -completed- List<CartItemsEntity> previousOrders()

// In OrderServiceImpl--//
//Partly completed-> List<CartItemsEntity> previousOrders()


//9.17------------------------------------------------------
// In OrderServiceImpl--//
//NO.3-below to save ship and bill address if said to save.
// save shipping and billing separate tabulations,under customer code
// ERROR--
//Parameter 4 of constructor in org.example.service.impl.OrderServiceImpl required a bean of type 'org.example.entity.OrderBillingAddressDataEntity' that could not be found.
//  Action:
// Consider defining a bean of type 'org.example.entity.OrderBillingAddressDataEntity' in your configuration.
// ERROR - SOLVED-NOTE for error is in the same file


//From 9.16, the latest will be on the top of this page
//9.16--------------------------------------------------------
// In Test class-CartItemListRepositoryTest--//

// completed the 1st test ()-saveCartItemListWithCartItemsTest
//      for List of (2) cartItemsEntities
//      -saving an order with cartItems and cartItemList

// In OrderServiceImpl--//

//NO.2-below to save order item details under generating new order code
//       with including customer code (customerCodeFromDB)

// In OrderController--//

// Giving a new path address for
// existing path address by diverting the existing path address ()
//@PostMapping("/save-email")
// //@PostMapping(path = "/save-email")   <-- This too correct
//public ResponseEntity<String> saveAndEmailOrderDetails(@RequestBody OrderDto orderDto){
//    //(@RequestBody CustomerAndOrderData customerAndOrderData)
//    return orderService.saveAndEmailOrderDetails(orderDto);
//}
//    //Old path address is giving here new address is above "/save-email"
////Check old practically latter and specify ok or not
//    @PostMapping("/old-save-email")
//    public ResponseEntity<Void> saveAndEmailOrderDetailsOld(@RequestBody OrderDto orderDto){
//        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
//                .header("Location", "/order-details//save-email")
//                .build();

//cartItemsListRepository.save(cartItemsListEntity)--
// will cartItemsEntity too save in DB by this code ?
// YES.
// it needs below facts to be inserted in to @OneToMany Annotation methods
// @OneToMany(... , cascade = CascadeType.ALL, orphanRemoval = true)
//1-CascadeType.ALL: in the `Customer` entity means that when you save
// a `Customer`, all associated `Order` entities will be saved as well.
//2-Orphan Removal = true` ensures that if an `Order` is removed
// from the `orders` list in `Customer`, it will be deleted from the db as well.

////////////////////////////////////////////////////////////////////////
//now issue-9.3-NET
    //orderCode eka hemat entity ekatama (CartItemsListEn, CardDataEn,
    // OrderBilling & ShippingAddressDaraEn ) ekama orderCode eka
    // daanne kohomada?
        //after solving this go to save
                    // CardDataEn
                    // OrderBilling && ShippingAddressDataEn
// 9.4- did send cart item list into DB
// NOTE-return ResponseEntity.ok().build(); -->Chat
        // return type of this --> ResponseEntity<Void>

//9.5-one to many finished. check and do many to one side annotations and parameters

/////// ANNOTATIONS /////
//@SpringBootTest in test classes
//ex-
//@SpringBootTest
//public class CartItemsListRepositoryTest {
//}


//9.12-bellow issue arise ???
//explain why this error comes in spring boot ? Could not
//        determine recommended JdbcType for Java type
//        'org.example.entity.CartItemsEntity' -AS JPA cannt find a
//        table type to save it in the DB

//9.13-solve 9.12 issue by using (making) CartItemsEntity table and
//     referring list<CartItemsEntity> para to that that entity table

//9.14-completed the 1st test ()-saveCartItemListWithCartItemsTest
//      -saving an order with cartItems and cartItemList

//9.15-completed the 2nd test ()-getAllCartItemsListWithCartItems
//      -saving an order with cartItems and cartItemList

