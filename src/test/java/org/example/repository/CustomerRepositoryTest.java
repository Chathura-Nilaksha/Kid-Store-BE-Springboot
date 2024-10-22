package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @Test
    void saveCustomerEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerCode(1);
        customerEntity.setEmail("cnilaksha@gmail.com");
        customerEntity.setFirstName("Chathura");

        customerRepository.save(customerEntity);
    }
}
