package com.tracio.Tracio.controller;

import com.tracio.Tracio.dto.request.ProductCreationRequest;
import com.tracio.Tracio.dto.request.ProductUpdateRequest;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.dto.response.ProductCreationResponse;
import com.tracio.Tracio.dto.response.ProductUpdateResponse;
import com.tracio.Tracio.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductCreationResponse> createProduct(@RequestBody ProductCreationRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/products")
    public ResponseEntity<PageResponse<ProductCreationResponse>> getAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(productService.getAll(page, size));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> createProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductUpdateResponse> updateShop(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

}
