package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Request.GroupResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    @Query("{'detail.manufacturer':?0}")
    List<Car> findByManufacturer(String manufacturer);

    @Query("{'name':?0}")
    Optional<Car> findByName(String name);

    @Query("{'status':?0}")
    List<Car> findByStatus(boolean status);

    @Query("{'detail.seat':?0}")
    List<Car> findBySeats(int seat);

    @Query("{'detail.hasDriver':?0}")
    List<Car> findByHasDriver(boolean hasDriver);

    @Query("{'detail.hasDriver':?0}")
    List<Car> getHotCarByHasDriver(boolean hasDriver, Sort sort);

    @Query("{'detail.transmission':?0}")
    List<Car> findByTransmission(String transmission);

    @Query("{'price':?0}")
    List<Car> findByPrice(String price);

    @Aggregation(pipeline = {
            "{ '$group':{ '_id' : null ,'count' :{ $count:{} }, 'total':{ '$sum': $total }}}"
    })
    GroupResponse carCount();

    @Aggregation(pipeline = {
            "{ '$group':{ '_id' : '$manufacturer' ,'count' :{ $count:{} }, 'total':{ $count:{} }}}"
    })
    List<GroupResponse> getManu();
}
