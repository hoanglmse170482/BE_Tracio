package com.tracio.Tracio.controller;

import com.tracio.Tracio.dto.request.ShopCreationRequest;
import com.tracio.Tracio.dto.request.ShopUpdateRequest;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.dto.response.ShopCreationResponse;
import com.tracio.Tracio.dto.response.ShopDetailResponse;
import com.tracio.Tracio.dto.response.ShopUpdateResponse;
import com.tracio.Tracio.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/shops/create")
    public ResponseEntity<ShopCreationResponse> createShop(@RequestBody ShopCreationRequest request) {
        return ResponseEntity.ok().body(shopService.createShop(request));
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        shopService.deleteShopById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shops")
    public ResponseEntity<PageResponse<ShopDetailResponse>> createShop(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().body(shopService.getAll(page, size));
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<ShopUpdateResponse> updateShop(@PathVariable Long id, @RequestBody ShopUpdateRequest request) {
        return ResponseEntity.ok(shopService.updateShop(id, request));
    }

}
