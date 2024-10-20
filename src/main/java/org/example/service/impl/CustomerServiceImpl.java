package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

//@EnableJpaRepositories
@RequiredArgsConstructor
@Service
@Lazy
public class CustomerServiceImpl implements CustomerService { //this error came after extending from JpaRepo
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ResponseEntity<String> saveCustomerDetails(CustomerDto customerDto) {
        CustomerEntity savedCustomerEntity = customerRepository.
                                                save(modelMapper.map(customerDto, CustomerEntity.class));
        return ResponseEntity.ok("Details saved. Thank You.");
    }
}

//ResponseEntity<Boolean>
//(Objects.nonNull(department.getDepartmentName())
//if (Objects.nonNull(department.getDepartmentCode())
//            && !"".equalsIgnoreCase(
//                department.getDepartmentCode()))
