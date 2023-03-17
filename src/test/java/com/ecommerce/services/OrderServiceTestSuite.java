package com.ecommerce.services;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Orders;
import com.ecommerce.model.User;
import com.ecommerce.repositories.OrderRepository;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTestSuite {
    private static OrderService sut;
    private static OrderRepository mockOrderRepository;
    private static UserService mockUserService;

    @BeforeEach
    public void testPrep(){
        mockOrderRepository = mock(OrderRepository.class);
        mockUserService = mock(UserService.class);
        sut = new OrderService(mockOrderRepository, mockUserService);
    }

    @Test
    public void test_findAll_returnListOrderResponse(){
        List<Orders> orders = new ArrayList<>();

        sut.findAll();
        verify(mockOrderRepository, times(1)).findAll();
    }

    @Test
    public void test_findById_returnOrderResponse_givenValidId(){
        User validUser = spy(new User(1, "valid", "valid", "valid", "valid"));
        Orders validOrder = spy(new Orders(1, validUser, new Date(2000,12,12)));

        when(mockOrderRepository.findById(1)).thenReturn(Optional.of(validOrder));

        Orders actualOrder = sut.findById(1);

        Assertions.assertInstanceOf(Orders.class, actualOrder);
        verify(mockOrderRepository, times(1)).findById(anyInt());
    }

    @Test
    public void test_findById_throwResourceNotFoundException_givenInvalidId(){
        when(mockOrderRepository.findById(-10)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> sut.findById(-10));

        verify(mockOrderRepository, times(1)).findById(-10);
    }





}
