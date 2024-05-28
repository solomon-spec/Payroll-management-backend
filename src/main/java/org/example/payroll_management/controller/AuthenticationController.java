package org.example.payroll_management.controller;

import org.example.payroll_management.dto.LoginUserDTO;
import org.example.payroll_management.model.LoginResponse;
import org.example.payroll_management.model.User;
import org.example.payroll_management.service.AuthenticationService;
import org.example.payroll_management.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController{
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println(authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping("/")
    public String hello(){
        authenticationService.signup("sol@gmail.com", "password");
        return "hello";
    }
}
