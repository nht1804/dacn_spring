package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Model.Car;
import com.nttu.dacnsv.Request.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
    private MongoTemplate mongoTemplate;

    public ServiceResult searchCar(String name, int price, int seat, Boolean driver, String manufacturer, String transmission) {
        ServiceResult result = new ServiceResult();
        Query query = new Query();
        if (name != null) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }
        if (driver != null) {
            query.addCriteria(Criteria.where("detail.hasDriver").is(driver));
        }
        if (manufacturer != null) {
            query.addCriteria(Criteria.where("detail.manufacturer").is(manufacturer));
        }
        if (transmission != null) {
            query.addCriteria(Criteria.where("detail.transmission").is(transmission));
        }
        if (seat > 0) {
            query.addCriteria(Criteria.where("detail.seat").is(seat));
        }
        query.addCriteria(Criteria.where("price").lte(price));
        result.setData(mongoTemplate.find(query, Car.class, "Car"));
        return result;
    }
}
