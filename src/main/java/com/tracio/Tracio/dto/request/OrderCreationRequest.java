package com.tracio.Tracio.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderCreationRequest {
    private String customerName;
    private String customerPhone;
    private BigDecimal totalPrice;
    private String shippingAddress;
    private Integer orderStatusId;
}
