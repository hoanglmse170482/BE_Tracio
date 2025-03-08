package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private Long orderId;
    private String customerName;
    private String customerPhone;
    private BigDecimal totalPrice;
    private String shippingAddress;
    private Integer orderStatusId;
}
