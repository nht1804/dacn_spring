package com.nttu.dacnsv.Controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoException;
import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Model.UsersDetail;
import com.nttu.dacnsv.Service.CarService;
import com.nttu.dacnsv.Test;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Car")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private Test test;

    @GetMapping //get all car from database
    public List<Car> getAllCar() {
        return carService.getAll();
    }

    @GetMapping("/name={name}") //find a car by name from database
    public ResponseEntity findCarByName(@PathVariable("name") String name) {
        try {
            if (carService.findByName(name).isEmpty()) {
                return ResponseEntity.ok().body(carService.findByName(name));
            } else {
                return ResponseEntity.ok().body("There is no car named: " + name + " in database");
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @GetMapping("type={type}") //find a car by type in database
    public ResponseEntity findCarByType(@PathVariable("type") String type) {
        try {
            List<Car> c = carService.findByCarType(type);
            if (c.isEmpty()) {
                return ResponseEntity.ok().body(carService.findByCarType(type));
            } else {
                return ResponseEntity.ok().body("There is no car type named: " + type + " in database");
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }

    }

    @PostMapping //insert a car to database
    public ResponseEntity addCar(@RequestBody Car car) {
        try {
            if (carService.findByName(car.getName()).isEmpty()) {
                return ResponseEntity.ok().body(carService.insert(car));
            } else {
                return ResponseEntity.ok().body("This car's name: " + car.getName() + " is already in database");
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @PutMapping //update a car
    public ResponseEntity updateCar(@RequestBody Car car) {
        try {
            if (carService.findByName(car.getName()).isEmpty()) {
                carService.update(car);
                return ResponseEntity.ok().body("Update a car named:" + car.getName());
            } else {
                return ResponseEntity.ok().body("There is no car named: " + car.getName() + " in database");
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @DeleteMapping //delete a car
    public ResponseEntity deleteCar(@RequestBody ObjectNode objectNode) {
        String id = objectNode.get("id").asText();
        try {
            if (carService.findById(id).isEmpty()) {
                carService.delete(id);
                return ResponseEntity.ok().body("Deleted a car id:" + id);
            } else {
                return ResponseEntity.ok().body("There is no car id: " + id);
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }
}
