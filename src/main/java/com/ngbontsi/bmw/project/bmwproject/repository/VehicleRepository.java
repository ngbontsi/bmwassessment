package com.ngbontsi.bmw.project.bmwproject.repository;

import com.ngbontsi.bmw.project.bmwproject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
