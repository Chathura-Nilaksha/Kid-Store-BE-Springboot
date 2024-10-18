package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock           //class to attach a bean.
    CustomerRepository customerRepository;
    @InjectMocks    //Bean for bean attaching class.
    CustomerServiceImpl customerServiceImpl;

    @Test
    void CustomerServiceImpl_SaveCustomerDetails_ReturnsResponseEntity() {


    }
}
//    public ResponseEntity<String> saveCustomerDetails(CustomerDto customerDto) {
//        customerRepository.save(modelMapper.map(customerDto, CustomerEntity.class));
//        return ResponseEntity.ok("Details saved. Thank You.");
//    }