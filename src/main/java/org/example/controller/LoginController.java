package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.LoginDto;
import org.example.service.LoginService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173") //http://localhost:5173
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @Lazy
    private final LoginService loginService;
//    @Lazy
//    private final LoginService loginServiceOne;
//    @Lazy
//    private final LoginService loginServiceTwo;
    @PostMapping("/login-validation") //to validate when customer login to customer's account.
    public ResponseEntity<String> loginValidation(@RequestBody LoginDto loginDto){
        return loginService.loginValidation(loginDto.getEmail(), loginDto.getPassword());
    }
    @PostMapping("/change-password") //to change password.
    public ResponseEntity<String> changePassword(@RequestBody LoginDto loginDto){
        return loginService.changePassword(loginDto);
    }
}
