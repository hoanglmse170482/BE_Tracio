package com.tracio.Tracio.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderDetailCreationRequest {
    private Integer quantity;
    private BigDecimal price;
    private Long productId;
    private Long orderId;
}
