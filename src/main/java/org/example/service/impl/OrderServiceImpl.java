package org.example.service.impl;
import com.mysql.cj.result.IntegerValueFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CartItems;
import org.example.dto.CustomerAndOrderData;
import org.example.dto.OrderDto;
import org.example.dtoOutgoing.OrderDtoSendingToFE;
import org.example.dtoOutgoing.PreviousOrdersData;
import org.example.entity.*;
import org.example.exeptions.CustomerAndOrderDataRawNotExistingInDBTable;
import org.example.exeptions.EmailNotExisting;
import org.example.exeptions.NoPreviousOrders;
import org.example.repository.*;
import org.example.service.OrderService;
import org.hibernate.mapping.Array;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;
    final CartItemsListRepository cartItemsListRepository;
    final CartItems cartItems;
    final PreviousOrdersData previousOrdersData;
    final CustomerAndOrderDataRepository customerAndOrderDataRepository;
//    @Autowired
//    OrderBillingAddressDataEntity orderBillingAddressDataEntity;
            // Cant use constructor or autowired injection as @Entity annotated classes
            // object creations are not managed by spring boot framework.
            // It is managed by JPA.
            //Spring is managing-->
                // @RestController,@Service,@Repository,@Component. @Controller beans only.
//    @Autowired
//    OrderShippingAddressDataEntity orderShippingAddressDataEntity;

    @Override
    public ResponseEntity<String> saveAndEmailOrderDetails(@NotNull OrderDto orderDto) {
                                        //(CustomerAndOrderData customerAndOrderData)
        //NO.1-below to send email with order confirmation-to that currently has logged in
        // and send email with all required data to the seller to send the item to customer
        // before 7 days by mentioning the exact date calculation.
            //String emailAccountRegistered = customerRepository.findByEmail(orderDto.getCustomerAndOrderData().getEmail()); // customerAndOrderData // customerCode
        String emailAccountRegistered = orderDto.getCustomerAndOrderData().getEmail();
            // I assume that registered email is given in getCustomerAndOrderData().getEmail()
        //PageRequest pageRequest = PageRequest.of(0, 1); // Limit results to 1 row
        //Optional<Integer> customerCodeFromDB =   // did a change
        CustomerEntity customerEntityFromDb =
            customerRepository.findCustomerCodeByEmail(emailAccountRegistered);
        Integer customerCodeFromDB = customerEntityFromDb.getCustomerCode();
            //Now get help from 3rd party (check net) and send a mail to above email-include below
                    // 1.order success and thanking
                    // 2.shipping address contact number.
                    // 3.billing address contact number.

        //Integer customerCodeFromDB2 ; // did a change
        //customerCodeFromDB2 = customerCodeFromDB.getContent().get(0); // did a change

        //DONE
        //NO.2-below to save order item details under generating new order code
            // with including customer code (customerCodeFromDB)
        //DONE
        //NO.3-below to save ship and bill address if said to save.
        // save shipping and billing separate tabulations,under customer code
            //Integer customerCodeFromDB
            //private Boolean isSaveShippingDataInDB;
            //private Boolean isSaveBillingDataInDB;
        List<CartItemsEntity> entityList = orderDto.getCartItems().stream()
                .map(itemDTO -> {
                    CartItemsEntity entity = new CartItemsEntity();
                    //entity.setCustomerCode(customerCodeFromDB);

                    entity.setId(cartItems.getId());
                    entity.setName(cartItems.getName());
                    entity.setQuantity(cartItems.getQuantity());
                    entity.setPrice(cartItems.getPrice());

                    return entity;
                })
                .toList();

        CartItemsListEntity cartItemsListEntity = new CartItemsListEntity();

        cartItemsListEntity.setEntityList(entityList);
        cartItemsListEntity.setGrandTotal(orderDto.getGrandTotal());
        cartItemsListEntity.setCustomerCodeFromDB(customerCodeFromDB);

        OrderShippingAddressDataEntity orderShippingAddressDataEntity =
                                        new OrderShippingAddressDataEntity();
        if(orderDto.getIsSaveShippingDataInDB()){
            orderShippingAddressDataEntity.setCustomerCodeFromDB(customerCodeFromDB);
            orderShippingAddressDataEntity.setFirstName(orderDto.getCustomerAndOrderData().getFirstName());
            orderShippingAddressDataEntity.setLastName(orderDto.getCustomerAndOrderData().getLastName());
            orderShippingAddressDataEntity.setPhoneNumber(orderDto.getCustomerAndOrderData().getPhoneNumber());
            orderShippingAddressDataEntity.setEmail(orderDto.getCustomerAndOrderData().getEmail());
            orderShippingAddressDataEntity.setAddressLine1(orderDto.getCustomerAndOrderData().getAddressLine1());
            orderShippingAddressDataEntity.setAddressLine1(orderDto.getCustomerAndOrderData().getAddressLine2());
            orderShippingAddressDataEntity.setCountry(orderDto.getCustomerAndOrderData().getCountry());
            orderShippingAddressDataEntity.setDistrict(orderDto.getCustomerAndOrderData().getDistrict());

            cartItemsListEntity.setOrderShippingAddressDataEntity(orderShippingAddressDataEntity);
            //@ManyToOne(fetch = FetchType.LAZY)
            //@OneToOne(mappedBy = "orderShippingAddressDataEntity")
            //@OneToOne
            //@JoinColumn(name = "fk_order_code", nullable = false)
            //private CartItemsListEntity cartItemsListEntity;
        }
        OrderBillingAddressDataEntity orderBillingAddressDataEntity =
                                                new OrderBillingAddressDataEntity();
        if(orderDto.getIsSaveBillingDataInDB()){
            orderBillingAddressDataEntity.setCustomerCodeFromDB(customerCodeFromDB);
            orderBillingAddressDataEntity.setFirstNameBill(orderDto.getCustomerAndOrderData().getFirstName());
            orderBillingAddressDataEntity.setLastNameBill(orderDto.getCustomerAndOrderData().getLastName());
            orderBillingAddressDataEntity.setEmailBill(orderDto.getCustomerAndOrderData().getPhoneNumber());
            orderBillingAddressDataEntity.setAddressLine1Bill(orderDto.getCustomerAndOrderData().getEmail());
            orderBillingAddressDataEntity.setAddressLine2Bill(orderDto.getCustomerAndOrderData().getAddressLine1());
            orderBillingAddressDataEntity.setCountryBill(orderDto.getCustomerAndOrderData().getAddressLine2());
            orderBillingAddressDataEntity.setDistrictBill(orderDto.getCustomerAndOrderData().getCountry());

            cartItemsListEntity.setOrderBillingAddressDataEntity(orderBillingAddressDataEntity);

            //@OneToOne
            //@JoinColumn(name = "fk_order_code", nullable = false)
            //private CartItemsListEntity cartItemsListEntity;
        }

        cartItemsListRepository.save(cartItemsListEntity);

        // DONE
        //NO.4-send card data to 3rd party(to only deduct the amount)
        // card validation is doing on FE

        String ccName = orderDto.getCustomerAndOrderData().getCcName();
        String ccNumber = orderDto.getCustomerAndOrderData().getCcNumber();
        String ccExpiration = orderDto.getCustomerAndOrderData().getCcExpiration();
        String cccvv = orderDto.getCustomerAndOrderData().getCccvv();
        String cardType = orderDto.getCardType();
        Double grandTotal = orderDto.getGrandTotal();
            //Now can send above parameters to 3rd party to deduct the amount(grandTotal)
            // from the card & return a response whether transaction is ok or not
            //Doing card validation in FE.Then there if card is valid send all
            // objects and data to BE.Card data is not saving in our system.

        HttpHeaders headers = new HttpHeaders();
        headers.add("My-header-1", "MyValue-1");
        headers.add("My-header-2", "MyValue-2");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body("Here is the String Object-Saving is done!");
    }
    @Override
    public PreviousOrdersData previousOrders(String registeredEmail) {
        log.info("this is service layer path var"+registeredEmail);

        if (customerRepository.existsByEmail(registeredEmail)) { //released-11.10.check next day by post
        //Hibernate query: select ce1_0.customer_code from customer_entity ce1_0 where ce1_0.email=? limit ?

            boolean b = customerRepository.existsByEmail(registeredEmail);
            log.info(String.valueOf(b));
            log.info("INSIDE IF--this is service layer");
        //PageRequest pageRequest = PageRequest.of(0, 1); // Limit results to 1 nos rows
                            //customerCodeToViewPreviousOrders
            CustomerEntity comesCustomerEntityToViewPreviousOrders =
                    customerRepository.findCustomerCodeByEmail(registeredEmail);
        //Hibernate query: select ce1_0.customer_code,ce1_0.address_line1,ce1_0.address_line2,ce1_0.city,
            // ce1_0.district,ce1_0.email,ce1_0.first_name,ce1_0.gender,ce1_0.last_name,ce1_0.password,
            // ce1_0.phone_number1,ce1_0.phone_number2,ce1_0.whatsapp_number,ce1_0.year_of_birth
            // from customer_entity ce1_0 where ce1_0.email=?

            log.info("INSIDE IF--this is service layer--after error line");
            log.info(String.valueOf(comesCustomerEntityToViewPreviousOrders));

        //Now get list of orders with orderCode from comesCustomerEntityToViewPreviousOrders.
            //List<Integer> orderCodesListFromDB = cartItemsListRepository.findOrderCodeByCustomerCodeFromDB(customerCodeToViewPreviousOrders);
            //previousOrdersData.setOderCodesList(orderCodesListFromDB);

            Integer customerCodeToViewPreviousOrders = comesCustomerEntityToViewPreviousOrders.getCustomerCode();
            //use this to find the past orders' order codes of this customerCode owner.

            List<Integer> orderCodesOfPreviousOrders = cartItemsListRepository
                                                        .findOrderCodeByCustomerCodeFromDB(customerCodeToViewPreviousOrders);

            if(!orderCodesOfPreviousOrders.isEmpty()){
                PreviousOrdersData previousOrdersData1 = new PreviousOrdersData();

                List<OrderDtoSendingToFE> orderDtoSendingToFEListToRelevantOrderCodesList = new ArrayList<>();

                orderCodesOfPreviousOrders.forEach(orderCode -> {
                    OrderDtoSendingToFE orderDtoSendingToFE = new OrderDtoSendingToFE();
                    orderDtoSendingToFE.setOrderCode(orderCode);

                    //Now have to search relevant items to other fields of the " OrderDtoSendingToFE " using "orderCode"
                        // & complete the " OrderDtoSendingToFE " obj.Below are Other fields.
                    //1.CustomerAndOrderData customerAndOrderData-completed, but check next day
                    //2.String cardType
                    //3.List<CartItems> cartItems
                    //4.Double grandTotal
                        //1.completed, but check next day
                    Optional<CustomerAndOrderDataEntity> customerAndOrderDataEntityForPreviousOrderCode =
                            customerAndOrderDataRepository.findById(Long.valueOf(orderCode)) ;
                    if(customerAndOrderDataEntityForPreviousOrderCode.isPresent()){
                        CustomerAndOrderDataEntity existingCustomerAndOrderDataEntityForPreviousOrderCode =
                                customerAndOrderDataEntityForPreviousOrderCode.get();
                        CustomerAndOrderData existingCustomerAndOrderDataForPreviousOrder =
                                modelMapper.map(
                                        existingCustomerAndOrderDataEntityForPreviousOrderCode, CustomerAndOrderData.class
                                                );

                        orderDtoSendingToFE.setCustomerAndOrderData(existingCustomerAndOrderDataForPreviousOrder);
                    }else{
                        throw new CustomerAndOrderDataRawNotExistingInDBTable("CustomerAndOrderData raw is " +
                                "not existing in the Database Table for the orderCode of"+orderCode);
                    }

                        //2.





                    orderDtoSendingToFEListToRelevantOrderCodesList.add(orderDtoSendingToFE);
                });



                previousOrdersData1.setOrderDtoListSendingToFE(orderDtoSendingToFEListToRelevantOrderCodesList);

                return previousOrdersData1;

            }else throw new NoPreviousOrders("This existing customer hasn't " +
                                                                            "order anything yet");

        }else{                                                  //released-11.10.check next day by post
            log.info("INSIDE else--this is service layer");     //released-11.10.check next day by post
            throw new EmailNotExisting("Given email is NOT a registered email address");
        }
    }
}

//NOTE-Below is return without a body.This will return type of
//this is ResponseEntity<void>
//        return ResponseEntity.status(HttpStatus.OK)
//                .headers(headers)
//                .build();

//need entities for below.
// 1.ship address data-under cus code
// 2.bill address data-under cus code
// 3.order item data - under order code,(cus code too must there)

//NOTE-int array and ArrayList declaring.
// int[] array = new int[5]; // An array of 5 integers, all initialized to 0.
//int[] array = {1, 2, 3, 4, 5}; // Array with predefined values.
// ArrayList<Integer> arrayList = new ArrayList<>();