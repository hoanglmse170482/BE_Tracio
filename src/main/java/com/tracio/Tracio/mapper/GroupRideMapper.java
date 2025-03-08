package com.tracio.Tracio.mapper;

import com.tracio.Tracio.dto.response.GroupRideResponse;
import com.tracio.Tracio.entity.GroupRide;

public class GroupRideMapper {
    private GroupRideMapper() {}

    public static GroupRideResponse toRideResponse(GroupRide groupRide) {
        return GroupRideResponse.builder()
                .groupRideId(groupRide.getGroupRideId())
                .matchPassword(groupRide.getMatchPassword())
                .startTime(groupRide.getStartTime())
                .finishTime(groupRide.getFinishTime())
                .startPoint(groupRide.getStartPoint())
                .endPoint(groupRide.getEndPoint())
                .participants(groupRide.getParticipants())
                .matchStatus(groupRide.getMatchStatus())
                .routeId(groupRide.getRoute().getRouteId())
                .createdByUserName(groupRide.getCreatedBy().getName())
                .build();
    }
}
