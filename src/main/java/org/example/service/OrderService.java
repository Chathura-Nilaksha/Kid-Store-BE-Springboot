package org.example.service;
import org.example.dto.OrderDto;
import org.example.dtoOutgoing.PreviousOrdersData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<String> saveAndEmailOrderDetails(OrderDto orderDto);

    PreviousOrdersData previousOrders(String registeredEmail);
}
