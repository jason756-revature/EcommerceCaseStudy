package com.ecommerce.controller;


import com.ecommerce.dtos.OrderDetailRequest;
import com.ecommerce.dtos.OrderDetailResponse;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.repositories.OrderDetailRepository;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orderdetail")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class OrderDetailController {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailService orderDetailService;
    private final AuthService authService;

    @Autowired
    public OrderDetailController(OrderDetailRepository orderDetailRepository,OrderDetailService orderDetailService, AuthService authService) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailService = orderDetailService;
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable int id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            return ResponseEntity.ok(orderDetail.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest){
        return ResponseEntity.ok(orderDetailService.createOrderDetail(orderDetailRequest));
    }






}
