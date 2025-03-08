package com.tracio.Tracio.service;

import com.tracio.Tracio.dto.request.GroupRideRequest;
import com.tracio.Tracio.dto.response.GroupRideResponse;
import com.tracio.Tracio.mapper.GroupRideMapper;
import com.tracio.Tracio.model.GroupRide;
import com.tracio.Tracio.model.Route;
import com.tracio.Tracio.model.User;
import com.tracio.Tracio.repository.GroupRideRepository;
import com.tracio.Tracio.repository.RouteRepository;
import com.tracio.Tracio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupRideService {

    private final GroupRideRepository groupRideRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    public GroupRideResponse create(GroupRideRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Route routeOpt = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        GroupRide groupRide = GroupRide.builder()
                .matchPassword(request.getMatchPassword())
                .startTime(request.getStartTime())
                .finishTime(request.getFinishTime())
                .startPoint(request.getStartPoint())
                .endPoint(request.getEndPoint())
                .participants(request.getParticipants())
                .matchStatus(request.getMatchStatus())
                .route(routeOpt)
                .createdBy(user)
                .build();

        groupRideRepository.save(groupRide);

        return GroupRideMapper.toRideResponse(groupRide);
    }

    public GroupRideResponse getGroupRideById(Long id) {
        GroupRide groupRide = groupRideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupRide not found"));
        return GroupRideMapper.toRideResponse(groupRide);
    }
    public GroupRideResponse updateGroupRide(Long id, GroupRideRequest request) {
        GroupRide groupRide = groupRideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupRide not found"));

        groupRide.setMatchPassword(request.getMatchPassword());
        groupRide.setStartTime(request.getStartTime());
        groupRide.setFinishTime(request.getFinishTime());
        groupRide.setStartPoint(request.getStartPoint());
        groupRide.setEndPoint(request.getEndPoint());
        groupRide.setParticipants(request.getParticipants());
        groupRide.setMatchStatus(request.getMatchStatus());
        groupRideRepository.save(groupRide);

        return GroupRideMapper.toRideResponse(groupRide);
    }

    public void deleteGroupRide(Long id) {
        GroupRide groupRide = groupRideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupRide not found"));
        groupRideRepository.delete(groupRide);
    }

    public List<GroupRideResponse> getAllGroupRides() {
        return groupRideRepository.findAll()
                .stream()
                .map(GroupRideMapper::toRideResponse)
                .toList();
    }
}
