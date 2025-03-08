package com.tracio.Tracio.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GroupRideRequest {
    private String matchPassword;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String startPoint;
    private String endPoint;
    private Integer participants;
    private String matchStatus;
    private Long routeId;
}
