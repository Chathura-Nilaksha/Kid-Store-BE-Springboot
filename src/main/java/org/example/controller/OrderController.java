package org.example.controller;
import lombok.RequiredArgsConstructor;
import org.example.dto.CustomerAndOrderData;
import org.example.dto.OrderDto;
import org.example.dtoOutgoing.PreviousOrdersData;
import org.example.entity.CartItemsEntity;
import org.example.service.OrderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/order-details")
//@RequestMapping(value = "/order-details")    <-- This too correct
@RequiredArgsConstructor
public class OrderController {
    @Lazy
    final OrderService orderService;
    @PostMapping("/save-email")
    //@PostMapping(path = "/save-email")   <-- This too correct
    public ResponseEntity<String> saveAndEmailOrderDetails(@RequestBody OrderDto orderDto){
                                        //(@RequestBody CustomerAndOrderData customerAndOrderData)
        return orderService.saveAndEmailOrderDetails(orderDto);
    }

//Old path address is giving here new address is above "/save-email"
//Check old practically latter and specify ok or not
    @PostMapping("/old-save-email")
    public ResponseEntity<Void> saveAndEmailOrderDetailsOld(@RequestBody OrderDto orderDto){
        //(@RequestBody CustomerAndOrderData customerAndOrderData)
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", "/order-details//save-email")
                .build();
    }
    @GetMapping(path = "/my-previous-orders/{registeredEmail}")//in address not putting curly braces "{}"(for email)
            //CartItemsEntity <-- this might be another type.check later.
    public PreviousOrdersData previousOrders(@PathVariable String registeredEmail){
        return orderService.previousOrders(registeredEmail);
    }

}

//Additional Tips:
// Validation: You can use JSR-303 annotations (@Valid) in your DTO classes
//        to validate the incoming JSON data.
// Error Handling:Implement exception handling mechanisms (@ControllerAdvice, @ExceptionHandler)
//        in your controller to manage errors gracefully.

//Accessing-String property2 = outerObjectDTO.getInnerObject().getProperty2();
//NOTE-ResponseEntity.ok("Processed successfully");