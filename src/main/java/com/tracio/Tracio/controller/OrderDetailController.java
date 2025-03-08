package com.tracio.Tracio.controller;

import com.tracio.Tracio.dto.request.OrderDetailCreationRequest;
import com.tracio.Tracio.dto.response.OrderDetailCreationResponse;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/order-details")
    public ResponseEntity<OrderDetailCreationResponse> createOrderDetail(@RequestBody OrderDetailCreationRequest request) {
        return ResponseEntity.ok(orderDetailService.createOrderDetail(request));
    }

    @GetMapping("/order-details")
    public ResponseEntity<PageResponse<OrderDetailCreationResponse>> getAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(orderDetailService.getAll(page, size));
    }

    @DeleteMapping("/order-details/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
    
}
