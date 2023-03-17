package com.ecommerce.controller;


import com.ecommerce.dtos.RegisterRequest;
import com.ecommerce.dtos.UserResponse;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        return userService.registerUser(registerRequest);
    }

}
