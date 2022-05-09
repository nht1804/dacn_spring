package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Bill;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BillRepository extends MongoRepository<Bill, String> {
    @Query("{'status':?0")
    List<Bill> findByStatus(String status);
    @Query("{'customerID':'?0','status':'?1'}")
    List<Bill> findByUserAndStatus(String userName, String status);
    @Query("{'customerID':'?0'}")
    List<Bill> findByUser(String userName, Sort sort);
}
