package com.tracio.Tracio.service;

import com.tracio.Tracio.dto.request.OrderDetailCreationRequest;
import com.tracio.Tracio.dto.response.OrderDetailCreationResponse;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.entity.Order;
import com.tracio.Tracio.entity.OrderDetail;
import com.tracio.Tracio.entity.Product;
import com.tracio.Tracio.repository.OrderDetailRepository;
import com.tracio.Tracio.repository.OrderRepository;
import com.tracio.Tracio.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderDetailCreationResponse createOrderDetail(OrderDetailCreationRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        orderDetailRepository.save(orderDetail);

        return OrderDetailCreationResponse.builder()
                .orderDetailId(orderDetail.getOrderDetailId())
                .productName(orderDetail.getProduct().getName())
                .customerName(orderDetail.getOrder().getCustomerName())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .build();
    }

    public PageResponse<OrderDetailCreationResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrderDetail> orderDetailPage = orderDetailRepository.findAll(pageable);
        List<OrderDetailCreationResponse> responses = orderDetailPage.getContent()
                .stream().map(orderDetail -> OrderDetailCreationResponse.builder()
                        .orderDetailId(orderDetail.getOrderDetailId())
                        .productName(orderDetail.getProduct().getName())
                        .customerName(orderDetail.getOrder().getCustomerName())
                        .quantity(orderDetail.getQuantity())
                        .price(orderDetail.getPrice())
                        .build())
                .toList();

        return PageResponse.<OrderDetailCreationResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(orderDetailPage.getTotalPages())
                .totalElements(orderDetailPage.getTotalElements())
                .data(responses)
                .build();
    }

    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found"));
        orderDetailRepository.delete(orderDetail);
    }

}
