package org.example.service;

import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<String> saveCustomerDetails(CustomerDto customerDto);


}
