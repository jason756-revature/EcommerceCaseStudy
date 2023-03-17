package com.ecommerce.dtos;


import com.ecommerce.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private int id;
    private int ordersId;
    private int productId;
    @NotBlank
    private float quantity;

    public OrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.ordersId = orderDetail.getOrderId().getId();
        this.productId = orderDetail.getProductId().getId();
        this.quantity = orderDetail.getQuantity();
    }
}
