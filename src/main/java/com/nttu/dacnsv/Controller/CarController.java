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

    @GetMapping("/status/{status}")
    public ResponseEntity<ServiceResult> findCarByStatus(@PathVariable("status") boolean status) {
        return ResponseEntity.ok().body(carService.findByStatus(status));
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<ServiceResult> findCarByStatus(@PathVariable("manufacturer") String manufacturer) {
        return ResponseEntity.ok().body(carService.findByManufacturer(manufacturer));
    }
    @GetMapping("/hasDriver/{hasDriver}")
    public ResponseEntity<ServiceResult> findCarByHasDriver(@PathVariable("hasDriver") boolean hasDriver) {
        return ResponseEntity.ok().body(carService.findByHasDriver(hasDriver));
    }
    @GetMapping("/HotCar")
    public ResponseEntity<ServiceResult> getHotCarByHasDriver(@RequestParam("d") boolean d) {
        return ResponseEntity.ok().body(carService.getHotCarByHasDriver(d));
    }
    @GetMapping("/random")
    public ResponseEntity<ServiceResult> getHotCarByHasDriver(@RequestParam int size) {
        return ResponseEntity.ok().body(carService.getRandomCar(size));
    }
    @GetMapping("/seat/{seat}")
    public ResponseEntity<ServiceResult> findCarByHasDriver(@PathVariable("seat") int seat) {
        return ResponseEntity.ok().body(carService.findBySeat(seat));
    }
    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<ServiceResult> findCarByTransmission(@PathVariable("seat") String transmission) {
        return ResponseEntity.ok().body(carService.findByTransmission(transmission));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ServiceResult> findCarById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(carService.findById(id));
    }
    @GetMapping("/ManuFactor")
    public ResponseEntity<ServiceResult> getAllManuFactor() {
        return ResponseEntity.ok().body(carService.getCarManuFactor());
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
