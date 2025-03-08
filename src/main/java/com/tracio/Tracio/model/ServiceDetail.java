package com.tracio.Tracio.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceDetailId;

    private BigDecimal totalPrice;
    private String description;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
}
