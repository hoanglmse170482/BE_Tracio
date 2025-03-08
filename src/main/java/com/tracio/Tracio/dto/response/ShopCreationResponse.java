package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class ShopCreationResponse implements Serializable {
    private Long shopId;
    private String name;
    private String address;
    private String contactInfo;
    private String operatingHours;
    private String ownerName; // Tên người sở hữu
}
