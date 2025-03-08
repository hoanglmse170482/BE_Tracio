package com.tracio.Tracio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupRideId;

    private String matchPassword;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String startPoint;
    private String endPoint;
    private Integer participants;
    private String matchStatus;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}
