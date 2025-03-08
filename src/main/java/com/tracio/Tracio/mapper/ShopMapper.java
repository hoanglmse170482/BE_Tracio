package com.tracio.Tracio.mapper;

import com.tracio.Tracio.dto.request.ShopCreationRequest;
import com.tracio.Tracio.dto.response.ShopCreationResponse;
import com.tracio.Tracio.entity.Shop;

public class ShopMapper {

    private ShopMapper() {
    }

    public static Shop toShop(ShopCreationRequest request){
        return Shop.builder()
                .name(request.getName())
                .address(request.getAddress())
                .contactInfo(request.getContactInfo())
                .operatingHours(request.getOperatingHours())
                .build();
    }

    public static ShopCreationResponse creationResponse(Shop shop) {
        return ShopCreationResponse.builder()
                .shopId(shop.getShopId())
                .name(shop.getName())
                .address(shop.getAddress())
                .contactInfo(shop.getContactInfo())
                .operatingHours(shop.getOperatingHours())
                .ownerName(shop.getOwner().getName())
                .build();
    }
}
