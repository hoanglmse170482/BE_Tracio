package com.tracio.Tracio.controller;

import com.tracio.Tracio.dto.request.GroupRideRequest;
import com.tracio.Tracio.dto.response.GroupRideResponse;
import com.tracio.Tracio.service.GroupRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/group-rides")
@RequiredArgsConstructor
public class GroupRideController {

    private final GroupRideService groupRideService;

    @PostMapping
    public ResponseEntity<GroupRideResponse> createGroupRide(@RequestBody GroupRideRequest request) {
        return ResponseEntity.ok(groupRideService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GroupRideResponse>> getAllGroupRides() {
        return ResponseEntity.ok(groupRideService.getAllGroupRides());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupRideResponse> getGroupRideById(@PathVariable Long id) {
        return ResponseEntity.ok(groupRideService.getGroupRideById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupRideResponse> updateGroupRide(@PathVariable Long id, @RequestBody GroupRideRequest request) {
        return ResponseEntity.ok(groupRideService.updateGroupRide(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroupRide(@PathVariable Long id) {
        groupRideService.deleteGroupRide(id);
        return ResponseEntity.ok("GroupRide deleted successfully");
    }
}
