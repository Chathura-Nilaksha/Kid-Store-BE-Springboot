package org.example.repository.Native;

import org.springframework.data.domain.PageRequest;

public interface CustomerNativeRepository {
    Integer findCustomerCodeByEmail(String email);
}
