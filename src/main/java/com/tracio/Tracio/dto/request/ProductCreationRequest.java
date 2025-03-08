package com.tracio.Tracio.dto.request;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class ProductCreationRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private String type;
    private Integer quantity;
    private Long shopId;
    private Long warrantyId;
}
