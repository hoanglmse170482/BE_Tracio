package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShopUpdateResponse {
    private Long shopId;
    private String name;
    private String address;
    private String contactInfo;
    private String operatingHours;
    private String ownerName;
}
