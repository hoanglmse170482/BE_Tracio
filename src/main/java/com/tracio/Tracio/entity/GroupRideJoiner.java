package com.tracio.Tracio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_ride_joiner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupRideJoiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupRideJoinerId;

    @ManyToOne
    @JoinColumn(name = "groupRide_id", nullable = false)
    private GroupRide groupRide;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
