package org.example.service;

import org.example.dto.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    void saveLoginDetails(String email, String password);
    ResponseEntity<String> loginValidation(String email, String password);
    ResponseEntity<String> changePassword(LoginDto loginDto);
}
