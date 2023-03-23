package com.revature.services;

import com.revature.dtos.CreateOrderRequest;
import com.revature.dtos.EditOrderRequest;
import com.revature.dtos.OrderResponse;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Order;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, PaymentService paymentService) {

        this.orderRepository = orderRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest, User user) {
        Order newOrder = new Order();
        Payment foundPayment = paymentService.findPaymentById(createOrderRequest.getPaymentId());
        newOrder.setUserId(user);
        newOrder.setPaymentId(foundPayment);
        newOrder.setOrderDate(new Date(System.currentTimeMillis()));
        newOrder.setShipmentAddress(createOrderRequest.getShipmentAddress());

        orderRepository.save(newOrder);

        OrderResponse orderResponse = new OrderResponse(newOrder);

        return orderResponse;
    }

    @Transactional
    public List<OrderResponse> findAll() {
        ArrayList<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order o : orders) {
            OrderResponse orderResponse = new OrderResponse(o);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }


    @Transactional
    public List<OrderResponse> findAllUserOrders(User user) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findByUserId(user);
        orderResponses = orders.stream().map(OrderResponse::new).collect(Collectors.toList());
        return orderResponses;
    }


    @Transactional
    public Order findById(int id) {
        return orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public OrderResponse update(EditOrderRequest editOrderRequest, User user) throws UnauthorizedException {
        System.out.println(editOrderRequest.getOrderId());
        Order foundOrder = findById(editOrderRequest.getOrderId());
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if (foundOrder.getUserId().getId() != user.getId()) {
            throw new UnauthorizedException("Not authorized to change this order.");
        }
        if (notNullOrEmpty.test(editOrderRequest.getPaymentId()) && notNullOrEmpty.test(editOrderRequest.getPaymentId())) {
            foundOrder.setPaymentId(paymentService.findPaymentById(editOrderRequest.getPaymentId()));
        }
        if (editOrderRequest.getShipmentAddress() != null && editOrderRequest.getShipmentAddress() != "") {
            foundOrder.setShipmentAddress(editOrderRequest.getShipmentAddress());
        }
        OrderResponse orderResponse = new OrderResponse(foundOrder);
        return orderResponse;
    }



}
