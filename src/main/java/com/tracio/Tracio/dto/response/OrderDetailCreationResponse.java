package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderDetailCreationResponse {
    private Long orderDetailId;
    private String productName;
    private String customerName;
    private Integer quantity;
    private BigDecimal price;
}
