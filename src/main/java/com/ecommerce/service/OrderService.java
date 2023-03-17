package com.ecommerce.service;

import com.ecommerce.dtos.OrderResponse;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Orders;
import com.ecommerce.model.User;
import com.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;


    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }


    @Transactional
    public List<OrderResponse> findAll() {
        ArrayList<OrderResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();
        for (Orders o : orders) {
            OrderResponse orderResponse = new OrderResponse(o);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Transactional
    public List<OrderResponse> findAllUserOrders(User user) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = orderRepository.findByUserId(user);
        orderResponses = orders.stream().map(OrderResponse::new).collect(Collectors.toList());
        return orderResponses;
    }

    @Transactional
    public Orders findById(int id) {
        return orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }


}
