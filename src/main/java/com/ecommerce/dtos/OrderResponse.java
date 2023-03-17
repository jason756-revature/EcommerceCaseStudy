package com.ecommerce.dtos;


import com.ecommerce.model.Orders;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class OrderResponse {

    private int orderId;
    private String userEmail;

    private Date orderDate;

    public OrderResponse(Orders order) {
        this.orderId = order.getId();
        this.userEmail = order.getUserId().getEmail();
        this.orderDate = order.getOrderDate();
    }

}
