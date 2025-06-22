package com.moptra.hrms.controller;

import com.moptra.hrms.model.AuthRequest;

import com.moptra.hrms.model.AuthResponse;
import com.moptra.hrms.model.Users;
import com.moptra.hrms.service.EmployeeService;
import com.moptra.hrms.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeService employeeService;

    /*First register the user in the database*/

    @PostMapping("/register")
    @Operation(summary = "Register user details")
    public ResponseEntity<?> register(@RequestBody Users user){
        try{
            employeeService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Generate jwt token")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            AuthResponse authResponse = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

