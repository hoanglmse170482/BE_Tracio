package com.tracio.Tracio.dto.request;

import lombok.Getter;

@Getter
public class ShopUpdateRequest {
    private String name;
    private String address;
    private String contactInfo;
    private String operatingHours;
}
