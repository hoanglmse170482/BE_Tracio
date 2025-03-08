package com.tracio.Tracio.repository;

import com.tracio.Tracio.model.GroupRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRideRepository extends JpaRepository<GroupRide, Long> {
}
