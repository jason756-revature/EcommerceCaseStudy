package com.ecommerce.service;

import com.ecommerce.dtos.OrderDetailRequest;
import com.ecommerce.dtos.OrderDetailResponse;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductService productService, OrderService orderService){
        this.orderDetailRepository=orderDetailRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    public OrderDetailResponse createOrderDetail(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = new OrderDetail();
        Orders foundOrder = orderService.findById(orderDetailRequest.getOrderId());
        Product foundProduct = productService.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new ResourceNotFoundException("No product found ."));
        orderDetail.setOrderId(foundOrder);
        orderDetail.setProductId(foundProduct);
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetailRepository.save(orderDetail);
        return new OrderDetailResponse(orderDetail);
    }

    @Transactional
    public List<OrderDetail> findAll(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetailRepository.findAll();
    }

    @Transactional
    public List<OrderDetailResponse> findAllOrderDetailsByOrder(int id){
        Orders foundOrder = orderService.findById(id);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(foundOrder);
        List<OrderDetailResponse> orderDetailResponses = orderDetails.stream().map(OrderDetailResponse::new).collect(Collectors.toList());
        return orderDetailResponses;
    }

    @Transactional
    public Optional<OrderDetail> findById(int id) {
        return orderDetailRepository.findById(id);
    }



}
