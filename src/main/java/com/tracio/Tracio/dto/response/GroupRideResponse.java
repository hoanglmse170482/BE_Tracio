package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GroupRideResponse {
    private Long groupRideId;
    private String matchPassword;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String startPoint;
    private String endPoint;
    private Integer participants;
    private String matchStatus;
    private Long routeId;
    private String createdByUserName;
}
