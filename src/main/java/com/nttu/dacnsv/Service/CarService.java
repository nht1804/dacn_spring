package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Request.GroupResponse;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private MongoTemplate mongoTemplate;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(carRepository.findAll());
        return result;
    }
    public ServiceResult getHotCarByHasDriver(boolean hasDriver) {
        ServiceResult result = new ServiceResult();
        result.setData(carRepository.getHotCarByHasDriver(hasDriver,Sort.by(Sort.Direction.DESC, "count")));
        result.setMessage("SUCCESS");
        return result;
    }
    public ServiceResult getRandomCar(int size) {
        ServiceResult result = new ServiceResult();
        Aggregation aggregation = newAggregation(Aggregation.sample(size));
        result.setData(mongoTemplate.aggregate(aggregation,"Car",Car.class).getMappedResults());
        result.setMessage("SUCCESS");
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

    public ServiceResult findBySeat(int number) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findBySeats(number);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Manufacturer Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

    public ServiceResult findByHasDriver(boolean hasDriver) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findByHasDriver(hasDriver);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Not Found");
        } else {
            result.setMessage("Success");
            result.setData(c);
        }
        return result;
    }

    public ServiceResult findByTransmission(String transmission) {
        ServiceResult result = new ServiceResult();
        List<Car> c = carRepository.findByTransmission(transmission);
        if (c.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Not Found");
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

    public ServiceResult getCarManuFactor() {
        ServiceResult result = new ServiceResult();
        result.setMessage("SUCCESS");
        Aggregation aggregation = newAggregation(Aggregation.group("$detail.manufacturer").count().as("count").count().as("total"));
        result.setData(mongoTemplate.aggregate(aggregation,"Car", GroupResponse.class).getMappedResults());
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
