package com.example.secureapi.controller;

import com.example.secureapi.dto.AuthRequest;
import com.example.secureapi.dto.AuthResponse;
import com.example.secureapi.model.User;
import com.example.secureapi.service.UserService;
import com.example.secureapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = new User();
            user.setUsername(authRequest.getUsername());
            user.setPassword(authRequest.getPassword());
            user.setRoles(Set.of("USER")); // Default role
            
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok("User registered successfully: " + savedUser.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            User user = userService.findByUsername(authRequest.getUsername());
            final String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
        }
    }
} 