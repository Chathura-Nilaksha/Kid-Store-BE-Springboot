package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class) // This to enable Mockito in this test class
class CustomerServiceImplTest {
    @Mock           //class that needs to attach a bean.
    private CustomerRepository customerRepository;
    //Injects the mock repository into the CustomerServiceImpl,
    // allowing to test the service without needing a real repository.
    @InjectMocks    //Bean for above '@Mock bean' including class.
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    // Check below from-c
    void CustomerServiceImpl_SaveCustomerDetails_ReturnsResponseEntityTest() {
        CustomerDto customerDtoObject = CustomerDto.builder()
                                    .customerCode(1001)
                                    .email("abcd@gmail.com")
                                    .city("Panadura")
                                    .addressLine2("Walana")
                                    .district("Kalutara")
                                    .gender("Male")
                                    .firstName("Chathura")
                                    .lastName("Karunasena")
                                    .password("12345")
                                    .phoneNumber1("0771234567")
                                    .whatsappNumber("0771234567")
                                        .build();
        log.info(String.valueOf(customerDtoObject));
                    // Below I think no need to assign to a CustomerEntity variable.
        CustomerEntity responseCustomerEntity = new CustomerEntity();
        OngoingStubbing<CustomerEntity> customerEntityOngoingStubbing = when(customerRepository.save(modelMapper.map(customerDtoObject, CustomerEntity.class)))
                .thenReturn(responseCustomerEntity);
        /*
        CustomerEntity savedCustomerEntity = customerRepository
                                                .save(modelMapper.map(customerDtoObject, CustomerEntity.class));
        */
        ResponseEntity<String> responseEntityForTesting = customerServiceImpl.saveCustomerDetails(customerDtoObject);
                    //Below Assertions--> import from assertj.core. NOT JUnit
        Assertions.assertThat(responseEntityForTesting).isEqualTo(ResponseEntity.ok("Details saved. Thank You.")) ;


    }
}
//    public ResponseEntity<String> saveCustomerDetails(CustomerDto customerDto) {
//        customerRepository.save(modelMapper.map(customerDto, CustomerEntity.class));
//        return ResponseEntity.ok("Details saved. Thank You.");
//    }