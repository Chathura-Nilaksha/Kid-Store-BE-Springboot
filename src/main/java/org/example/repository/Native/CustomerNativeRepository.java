package org.example.repository.Native;

import org.example.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerNativeRepository {
    Integer findCustomerCodeByEmail(String email);
    Optional<CustomerEntity> findCustomerAllDetailsByEmail(String email);

}
