package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(carRepository.findAll());
        return result;
    }

    public ServiceResult insert(Car car) {
        ServiceResult result = new ServiceResult();
        Car c = carRepository.findByName(car.getName()).orElse(null);
        if (c != null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("This Car is already in database");
        } else {
            carRepository.insert(car);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult update(Car car) {
        ServiceResult result = new ServiceResult();
        Car c = carRepository.findById(car.getId()).orElse(null);
        if (c == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Car Not Found");
        } else {
            carRepository.save(car);
            result.setMessage("Success");
        }
        return result;


    }

    public ServiceResult delete(String id) {
        ServiceResult result = new ServiceResult();
        Car c = carRepository.findById(id).orElse(null);
        if (c == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Car Not Found");
        } else {
            carRepository.delete(c);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult findById(String id) {
        ServiceResult result = new ServiceResult();
        Car c = carRepository.findById(id).orElse(null);
        if (c == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Car Not Found");
        } else {
            result.setMessage("Success");
            result.setData(carRepository.findById(id));
        }
        return result;
    }

    public ServiceResult findByManufacturer(String manufacturer) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findByManufacturer(manufacturer);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Manufacturer Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

    public ServiceResult findByCarType(String carType) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findByCarType(carType);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Manufacturer Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

    public ServiceResult findByName(String name) {
        ServiceResult result = new ServiceResult();
        Car c = carRepository.findByName(name).orElse(null);
        if (c == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Manufacturer Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

    public ServiceResult findByStatus(boolean status) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findByStatus(status);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Manufacturer Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

}
