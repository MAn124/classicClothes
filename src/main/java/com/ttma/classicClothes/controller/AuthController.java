package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.AuthenticationService;
import com.ttma.classicClothes.Service.JwtService;
import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.dto.request.EmailConfirmRequest;
import com.ttma.classicClothes.dto.request.LoginRequest;
import com.ttma.classicClothes.dto.response.ResponseToken;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    @Operation(summary = "Login", description = "Login")
    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody LoginRequest request){
        try {
            return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
        }catch (Exception e){
            log.error("error message: {}",e.getMessage(),e.getCause());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Confirm email", description = "Confirm email")
    @PostMapping("/confirm-email")
    public ResponseEntity<?> confirmEmail(@RequestBody EmailConfirmRequest request){
        try {
           userService.confirmEmail(request.getEmail(), request.getConfirmationCode());
           return ResponseEntity.ok().body("Email confirm successfully");
        }catch (BadCredentialsException e){
            log.error("error message: {}",e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body("Invalid");
        }
    }
}
