package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductCreationResponse {
    private String name;
    private BigDecimal price;
    private String description;
    private String type;
    private Integer quantity;
    private String shopName;
    private String warrantyName;
}
