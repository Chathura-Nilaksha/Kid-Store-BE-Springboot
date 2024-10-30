package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

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

    // Below test () used new things
        //1. "when" --when().thenReturn()
        //2. "Assertions" --Assertions.assertThat().isEqualTo()     --> import from assertj.core. NOT JUnit
        //3. "verify" --verify(customerRepository, times()).save() --> import from MOCKITO
    @Test
    void CustomerServiceImpl_SaveCustomerDetails_Returns_SuccessResponseEntityTest() {
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

            //Assume below "asSavedCustomerEntity" as returning entity after saving the Dto object.
        CustomerEntity asSavedCustomerEntity = new CustomerEntity();

        OngoingStubbing<CustomerEntity> customerEntityOngoingStubbing;
        customerEntityOngoingStubbing = when(
                                            customerRepository.save(modelMapper.map(customerDtoObject, CustomerEntity.class))
                                            )
                                            .thenReturn(asSavedCustomerEntity);

        ResponseEntity<String> responseEntityForTesting = customerServiceImpl.saveCustomerDetails(customerDtoObject);

        org.junit.jupiter.api.Assertions.assertNotNull(responseEntityForTesting);

            // "Assertions"--> import from assertj.core. NOT JUnit
        Assertions.assertThat(responseEntityForTesting).isEqualTo(ResponseEntity.ok("Details saved. Thank You.")) ;

            // "verify" --> import from MOCKITO
        verify(customerRepository, times(1)).save(asSavedCustomerEntity);
    }

    @Test
    void CustomerServiceImpl_SaveCustomerDetails_Returns_FailureResponseEntityTest() {
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

        //Assume below "asSavedCustomerEntity" as returning entity after saving the Dto object.
        CustomerEntity asSavedCustomerEntity = new CustomerEntity();

        OngoingStubbing<CustomerEntity> customerEntityOngoingStubbing;
        customerEntityOngoingStubbing = when(
                customerRepository.save(modelMapper.map(customerDtoObject, CustomerEntity.class))
                ).thenThrow(RuntimeException.class);

        ResponseEntity<String> responseEntityForTesting = customerServiceImpl.saveCustomerDetails(customerDtoObject);

        org.junit.jupiter.api.Assertions.assertNotNull(responseEntityForTesting);

        // "Assertions"--> import from assertj.core. NOT JUnit
        Assertions.assertThat(responseEntityForTesting)
                  .isEqualTo(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                           .body("Data not saved due to internal Server error")) ;

//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntityForTesting.getStatusCode());  // HTTP 500 Internal Server Error
//        assertEquals("Error saving customer details", responseEntityForTesting.getBody());

        // "verify" --> import from MOCKITO
        verify(customerRepository, times(1)).save(asSavedCustomerEntity);
    }
}

//Below was-c-10.20
//when(customerRepository.save(any(CustomerEntity.class)))
//            .thenThrow(new RuntimeException("Database error"));
//assertNotNull(responseEntityForTesting);
//assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntityForTesting.getStatusCode());  // HTTP 500 Internal Server Error
//assertEquals("Error saving customer details", responseEntityForTesting.getBody());

//try {
//        CustomerEntity savedCustomerEntity = customerRepository.
//        save(modelMapper.map(customerDto, CustomerEntity.class));
//        return ResponseEntity.ok("Details saved. Thank You.");
//
//        }catch (Exception e){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//        .body("Data not saved due to internal Server error");
//        }

//    public ResponseEntity<String> saveCustomerDetails(CustomerDto customerDto) {
//        customerRepository.save(modelMapper.map(customerDto, CustomerEntity.class));
//        return ResponseEntity.ok("Details saved. Thank You.");
//    }