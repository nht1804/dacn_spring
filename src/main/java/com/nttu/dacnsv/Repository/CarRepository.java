package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends MongoRepository<Car,String> {
    @Query("{'manufacturer':?0}")
    List<Car> findByManufacturer(String manufacturer);
    @Query("{'carType':?0}")
    List<Car> findByCarType(String carType);
    @Query("{'name':?0}")
    Optional<Car> findByName(String name);
    @Query("{'status':?0}")
    List<Car> findByStatus(boolean status);

}
