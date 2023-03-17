package com.ecommerce.controller;


import com.ecommerce.dtos.OrderResponse;
import com.ecommerce.model.Orders;
import com.ecommerce.model.User;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"},allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;

    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") int id) {
        Optional<Orders> optional = Optional.of(orderService.findById(id));
        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }



}
