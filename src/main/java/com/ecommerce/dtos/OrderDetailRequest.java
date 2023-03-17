package com.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private int productId;
    private int orderId;
    @NotBlank
    private int quantity;
}
