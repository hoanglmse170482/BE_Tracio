package com.tracio.Tracio.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ShopCreationRequest implements Serializable {
    private String name;
    private String address;
    private String contactInfo;
    private String operatingHours;
}
