package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;


    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car insert(Car car) {
        return carRepository.insert(car);
    }

    public void update(Car car){
        carRepository.save(car);
    }

    public void delete(String id){
        carRepository.deleteById(id);
    }

    public Optional<Car> findById(String id){
        return carRepository.findById(id);
    }

    public Optional<Car> findByManufacturer(String manufacturer){
        return carRepository.findByManufacturer(manufacturer);
    }

    public List<Car> findByCarType(String carType){
        return carRepository.findByCarType(carType);
    }

    public Optional<Car> findByName(String name){
        return carRepository.findByName(name);
    }

}
