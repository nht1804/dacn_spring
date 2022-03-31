package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Request.DeleteByIdRequest;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/Car")
@AllArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping //get all car from database
    public ResponseEntity<ServiceResult> getAllCar() {
        return ResponseEntity.ok().body(carService.getAll());
    }

    @GetMapping("/name/{name}") //find a car by name from database
    public ResponseEntity<ServiceResult> findCarByName(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(carService.findByName(name));
    }

    @GetMapping("/type/{type}") //find a car by type in database
    public ResponseEntity<ServiceResult> findCarByType(@PathVariable("type") String type) {
        return ResponseEntity.ok().body(carService.findByCarType(type));

    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ServiceResult> findCarByStatus(@PathVariable("status") boolean status) {
        return ResponseEntity.ok().body(carService.findByStatus(status));
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<ServiceResult> findCarByStatus(@PathVariable("manufacturer") String manufacturer) {
        return ResponseEntity.ok().body(carService.findByManufacturer(manufacturer));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ServiceResult> findCarById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(carService.findById(id));
    }

    @PostMapping //insert a car to database
    public ResponseEntity<ServiceResult> addCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.insert(car));
    }

    @PutMapping //update a car
    public ResponseEntity<ServiceResult> updateCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.update(car));
    }

    @DeleteMapping //delete a car
    public ResponseEntity<ServiceResult> deleteCar(@RequestBody DeleteByIdRequest request) {
        return ResponseEntity.ok().body(carService.delete(request.getId()));
    }
}
