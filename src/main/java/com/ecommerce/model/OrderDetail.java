package com.ecommerce.model;

import com.ecommerce.dtos.OrderDetailRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderId;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;
    private int quantity;

    public OrderDetail(OrderDetailRequest orderDetailRequest, Orders order, Product product){
        this.orderId=order;
        this.productId=product;
        this.quantity=orderDetailRequest.getQuantity();
    }

}
