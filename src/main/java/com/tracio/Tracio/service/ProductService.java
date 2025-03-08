package com.tracio.Tracio.service;

import com.tracio.Tracio.dto.request.ProductCreationRequest;
import com.tracio.Tracio.dto.request.ProductUpdateRequest;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.dto.response.ProductCreationResponse;
import com.tracio.Tracio.dto.response.ProductUpdateResponse;
import com.tracio.Tracio.model.Product;
import com.tracio.Tracio.model.Shop;
import com.tracio.Tracio.model.Warranty;
import com.tracio.Tracio.repository.ProductRepository;
import com.tracio.Tracio.repository.ShopRepository;
import com.tracio.Tracio.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final WarrantyRepository warrantyRepository;

    public ProductCreationResponse createProduct(ProductCreationRequest request) {
        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Warranty warranty = warrantyRepository.findById(request.getWarrantyId())
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .type(request.getType())
                .shop(shop)
                .warranty(warranty)
                .build();

        productRepository.save(product);

        return ProductCreationResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .type(product.getType())
                .shopName(shop.getName())
                .warrantyName(warranty.getName())
                .build();
    }

    public PageResponse<ProductCreationResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductCreationResponse> responses = productPage.getContent()
                .stream().map(product -> ProductCreationResponse.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .description(product.getDescription())
                        .type(product.getType())
                        .shopName(product.getShop().getName())
                        .warrantyName(product.getWarranty().getName())
                        .build())
                .toList();
        return PageResponse.<ProductCreationResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .data(responses)
                .build();
    }

    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public ProductUpdateResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if(request.getName() != null) {
            product.setName(request.getName());
        }
        if(request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if(request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if(request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }
        if(request.getType() != null) {
            product.setType(request.getType());
        }
        productRepository.save(product);
        return ProductUpdateResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .type(product.getType())
                .shopName(product.getShop().getName())
                .warrantyName(product.getWarranty().getName())
                .build();
    }

}
