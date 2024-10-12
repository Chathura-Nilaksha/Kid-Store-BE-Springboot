package org.example.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CartItems;
import org.example.dto.OrderDto;
import org.example.dtoOutgoing.PreviousOrdersData;
import org.example.entity.*;
import org.example.repository.*;
import org.example.service.OrderService;
import org.hibernate.mapping.Array;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;
    final CartItemsListRepository cartItemsListRepository;
    final CartItems cartItems;
    final PreviousOrdersData previousOrdersData;
//    @Autowired
//    OrderBillingAddressDataEntity orderBillingAddressDataEntity;
            // Cant use constructor or autowired injection as @Entity annotated classes
            // object creations are not managed by springboot framework.
            // It is managed by JPA.
            //Spring is managing-->
                // @RestController,@Service,@Repository,@Component. @Controller beans only.
//    @Autowired
//    OrderShippingAddressDataEntity orderShippingAddressDataEntity;


    @Override
    public ResponseEntity<String> saveAndEmailOrderDetails(OrderDto orderDto) {
                                        //(CustomerAndOrderData customerAndOrderData)
        //NO.1-below to send email with order confirmation-to that currently has logged in
        // and send email with all required data to the seller to send the item to customer
        // before 7 days by mentioning the exact date calculation.
            //String emailAccountRegistered = customerRepository.findByEmail(orderDto.getCustomerAndOrderData().getEmail()); // customerAndOrderData // customerCode
        String emailAccountRegistered = orderDto.getCustomerAndOrderData().getEmail();
            // I assume that registered email is given in getCustomerAndOrderData().getEmail()
        PageRequest pageRequest = PageRequest.of(0, 1); // Limit results to 1 row
        Integer customerCodeFromDB =
                customerRepository.findCustomerCodeByEmail(emailAccountRegistered, pageRequest);
            //Now get help from 3rd party (check net) and send a mail to above email-include below
                    // 1.order success and thanking
                    // 2.shipping address contact number.
                    // 3.billing address contact number.


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
        log.info("this is service layer");
        if (customerRepository.existsByEmail(registeredEmail)) {
        //Hibernate: select ce1_0.customer_code from customer_entity ce1_0 where ce1_0.email=? limit ?
            log.info("INSIDE IF--this is service layer");
            PageRequest pageRequest = PageRequest.of(0, 1); // Limit results to 1 row
            Integer customerCodeToViewPreviousOrders =
                    customerRepository.findCustomerCodeByEmail(registeredEmail, pageRequest);



            //Optional<Integer>
//            Integer customerCodeFromDB =
//                    customerRepository.findCustomerCodeByEmail(emailAccountRegistered);
            log.info(String.valueOf(customerCodeToViewPreviousOrders));
            //Now get list of orders with orderCode from customerCodeToViewPreviousOrders.
            //List<Integer> orderCodesListFromDB = cartItemsListRepository.findOrderCodeByCustomerCodeFromDB(customerCodeToViewPreviousOrders);
            //previousOrdersData.setOderCodesList(orderCodesListFromDB);
            return previousOrdersData;
        }else{
            return null;
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