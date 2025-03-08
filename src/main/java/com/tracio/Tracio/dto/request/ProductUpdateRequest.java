package com.tracio.Tracio.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductUpdateRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private String type;
    private Integer quantity;
}
