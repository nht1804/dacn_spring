package com.nttu.dacnsv.Controller;
import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Service.CarService;
import com.nttu.dacnsv.Test;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Car")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private Test test;

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

    @PostMapping //insert a car to database
    public ResponseEntity<ServiceResult> addCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.insert(car));
    }

    @PutMapping //update a car
    public ResponseEntity<ServiceResult> updateCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.update(car));
    }

    @DeleteMapping //delete a car
    public ResponseEntity<ServiceResult> deleteCar(@RequestBody String id) {
        return ResponseEntity.ok().body(carService.delete(id));
    }
}
