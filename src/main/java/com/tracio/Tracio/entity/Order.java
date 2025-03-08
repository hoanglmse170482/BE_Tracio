package com.tracio.Tracio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String customerName;
    private String customerPhone;
    private BigDecimal totalPrice;
    private String shippingAddress;
    private Integer orderStatusId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
