package com.tracio.Tracio.controller;

import com.tracio.Tracio.dto.request.OrderCreationRequest;
import com.tracio.Tracio.dto.response.OrderCreationResponse;
import com.tracio.Tracio.dto.response.OrderDetailResponse;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderCreationResponse> createOrder(@RequestBody OrderCreationRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/orders")
    public ResponseEntity<PageResponse<OrderDetailResponse>> createShop(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().body(orderService.getAll(page, size));
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

}
