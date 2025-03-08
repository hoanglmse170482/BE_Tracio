package com.tracio.Tracio.service;

import com.tracio.Tracio.dto.request.ShopCreationRequest;
import com.tracio.Tracio.dto.request.ShopUpdateRequest;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.dto.response.ShopCreationResponse;
import com.tracio.Tracio.dto.response.ShopDetailResponse;
import com.tracio.Tracio.dto.response.ShopUpdateResponse;
import com.tracio.Tracio.mapper.ShopMapper;
import com.tracio.Tracio.entity.Shop;
import com.tracio.Tracio.entity.User;
import com.tracio.Tracio.repository.ShopRepository;
import com.tracio.Tracio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    public ShopCreationResponse createShop(ShopCreationRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Shop shop = ShopMapper.toShop(request);
        shop.setOwner(owner);

        shopRepository.save(shop);
        return ShopMapper.creationResponse(shop);
    }

    public void deleteShopById(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Shop not found"));
        shopRepository.delete(shop);
    }

    public PageResponse<ShopDetailResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Shop> shopPage = shopRepository.findAll(pageable);

        List<ShopDetailResponse> shopDetailResponses = shopPage.getContent()
                .stream().map(shop -> ShopDetailResponse.builder()
                        .shopId(shop.getShopId())
                        .name(shop.getName())
                        .address(shop.getAddress())
                        .contactInfo(shop.getContactInfo())
                        .operatingHours(shop.getOperatingHours())
                        .ownerName(shop.getOwner().getName())
                        .build()).toList();

        return PageResponse.<ShopDetailResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(shopPage.getTotalPages())
                .totalElements(shopPage.getTotalElements())
                .data(shopDetailResponses)
                .build();
    }

    public ShopUpdateResponse updateShop(Long id, ShopUpdateRequest request) {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Shop not found"));
        if(request.getName() != null) {
            shop.setName(request.getName());
        }
        if(request.getAddress() != null) {
            shop.setAddress(request.getAddress());
        }
        if(request.getContactInfo() != null) {
            shop.setContactInfo(request.getContactInfo());
        }
        if(request.getOperatingHours() != null) {
            shop.setOperatingHours(request.getOperatingHours());
        }
        shopRepository.save(shop);
        return ShopUpdateResponse.builder()
                .shopId(shop.getShopId())
                .name(shop.getName())
                .address(shop.getAddress())
                .contactInfo(shop.getContactInfo())
                .operatingHours(shop.getOperatingHours())
                .build();
    }

}
