package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BillRepository extends MongoRepository<Bill, String> {
    @Query("{'status':?0}")
public List<Bill> findByStatus(String status);
}
