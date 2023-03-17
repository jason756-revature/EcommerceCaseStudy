package com.ecommerce.services;

import com.ecommerce.dtos.OrderDetailRequest;
import com.ecommerce.dtos.OrderDetailResponse;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repositories.OrderDetailRepository;
import com.ecommerce.repositories.OrderRepository;
import com.ecommerce.service.OrderDetailService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceTestSuite {
    private static OrderDetailService sut;
    private static OrderDetailRepository orderDetailRepository;

    private static OrderRepository orderRepository;
    private static ProductService productService;
    private static OrderService orderService;
    private List<OrderDetail> mockOrderDetails;

    @BeforeAll
    static void init() {
        orderRepository = mock(OrderRepository.class);
        orderDetailRepository = mock(OrderDetailRepository.class);
        productService = mock(ProductService.class);
        orderService = mock(OrderService.class);
        sut = new OrderDetailService(orderDetailRepository, productService, orderService);
    }

    @Test
    public void test_createOrderDetail_returnOrderDetailResponse_givenValidCreateOrderDetailRequest(){
        User validUser = spy(new User(1, "valid", "valid", "valid", "valid"));
        Product validProduct = spy(new Product(1, "valid", "valid", 1, 1, "valid"));
        Orders validOrder = spy(new Orders(1, validUser, new Date(2000,12,12)));
        OrderDetailRequest orderDetailRequest = spy(new OrderDetailRequest(1,1,1));

        when(orderService.findById(validOrder.getId())).thenReturn(validOrder);
        when(productService.findById(orderDetailRequest.getProductId())).thenReturn(Optional.of(validProduct));
        doReturn(new OrderDetail(orderDetailRequest, validOrder, validProduct)).when(orderDetailRepository).save(any(OrderDetail.class));

        OrderDetailResponse validOrderDetailResponse = sut.createOrderDetail(orderDetailRequest);
        Assertions.assertInstanceOf(OrderDetailResponse.class, validOrderDetailResponse);

        verify(orderDetailRepository, times(1)).save(any());

    }

    @Test
    public void test_find_all() {
        sut.findAll();
        verify(orderDetailRepository, times(2)).findAll();
    }

    @Test
    public void test_findById(){
        sut.findById(1);

        verify(orderDetailRepository, times(1)).findById(anyInt());
    }

    @Test
    public void test_find_all_OrderDetailsByOrder(){
        when(orderService.findById(anyInt())).thenReturn(new Orders());
        when(orderDetailRepository.findByOrderId(any(Orders.class))).thenReturn(new ArrayList<>());

        List<OrderDetailResponse> result = sut.findAllOrderDetailsByOrder(3);

        verify(orderService, times(2)).findById(anyInt());
        verify(orderDetailRepository, times(1)).findByOrderId(any(Orders.class));

        assertTrue(result != null);
    }




}
