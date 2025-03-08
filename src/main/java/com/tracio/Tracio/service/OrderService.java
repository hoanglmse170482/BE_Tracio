package com.tracio.Tracio.service;

import com.tracio.Tracio.dto.request.OrderCreationRequest;
import com.tracio.Tracio.dto.response.OrderCreationResponse;
import com.tracio.Tracio.dto.response.OrderDetailResponse;
import com.tracio.Tracio.dto.response.PageResponse;
import com.tracio.Tracio.model.Order;
import com.tracio.Tracio.model.User;
import com.tracio.Tracio.repository.OrderRepository;
import com.tracio.Tracio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderCreationResponse createOrder(OrderCreationRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Order order = Order.builder()
                .customerName(email)
                .customerPhone(request.getCustomerPhone())
                .totalPrice(request.getTotalPrice())
                .shippingAddress(request.getShippingAddress())
                .orderStatusId(1)
                .user(user)
                .build();

        orderRepository.save(order);

        return OrderCreationResponse.builder()
                .orderId(order.getOrderId())
                .customerName(email)
                .customerName(order.getCustomerName())
                .customerPhone(order.getCustomerPhone())
                .totalPrice(order.getTotalPrice())
                .shippingAddress(order.getShippingAddress())
                .orderStatusId(order.getOrderStatusId())
                .build();
    }

    public PageResponse<OrderDetailResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<OrderDetailResponse> responses = orderPage.getContent()
                .stream().map(order -> OrderDetailResponse.builder()
                        .orderId(order.getOrderId())
                        .customerName(order.getCustomerName())
                        .customerName(order.getCustomerName())
                        .customerPhone(order.getCustomerPhone())
                        .totalPrice(order.getTotalPrice())
                        .shippingAddress(order.getShippingAddress())
                        .orderStatusId(order.getOrderStatusId())
                        .build()).toList();

        return PageResponse.<OrderDetailResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(orderPage.getTotalPages())
                .totalElements(orderPage.getTotalElements())
                .data(responses)
                .build();
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
