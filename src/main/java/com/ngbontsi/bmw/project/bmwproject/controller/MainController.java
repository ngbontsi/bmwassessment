package com.ngbontsi.bmw.project.bmwproject.controller;


import com.ngbontsi.bmw.project.bmwproject.model.Vehicle;
import com.ngbontsi.bmw.project.bmwproject.repository.VehicleRepository;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MainController {
    @Autowired
    public final VehicleRepository vehicleRepository;
    @GetMapping("/vehicle/{id}")
    public Vehicle retrieveVehicle(@PathVariable long id) {
        Optional<Vehicle> student = vehicleRepository.findById(id);

        if (student.isEmpty())
            return null;

        return student.get();
    }

    @GetMapping("/")
    public List<Vehicle> getVehicle(){
    return  vehicleRepository.findAll();
    }

    @DeleteMapping("/vehicle/{id}")
    public void deleteVehicle(@PathVariable long id) {
        vehicleRepository.deleteById(id);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedVehicle.getVehicle_id()).toUri();

        return ResponseEntity.created(location).build();

    }
    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Vehicle vehicle, @PathVariable long id) {

        Optional<Vehicle> studentOptional = vehicleRepository.findById(id);

        if (studentOptional.isEmpty())
            return ResponseEntity.notFound().build();

        vehicle.setVehicle_id(id);

        vehicleRepository.save(vehicle);

        return ResponseEntity.noContent().build();
    }



}
