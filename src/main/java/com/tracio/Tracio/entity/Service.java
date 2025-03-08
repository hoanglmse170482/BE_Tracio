package com.tracio.Tracio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private Long shopId;
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
