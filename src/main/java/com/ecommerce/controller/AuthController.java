package com.ecommerce.controller;


import com.ecommerce.dtos.LoginRequest;
import com.ecommerce.dtos.RegisterRequest;
import com.ecommerce.dtos.UserResponse;
import com.ecommerce.model.User;
import com.ecommerce.service.AuthService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> userOptional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", userOptional.get());

        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(authService.register(created)));
    }

}
