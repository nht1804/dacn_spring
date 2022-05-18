package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Bill;
import com.nttu.dacnsv.Request.GroupResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BillRepository extends MongoRepository<Bill, String> {
    @Query("{'status':?0}")
    List<Bill> findByStatus(String status);

    @Query("{'customerID':'?0','status':'?1'}")
    List<Bill> findByUserAndStatus(String userName, String status);

    @Query("{'customerID':'?0'}")
    List<Bill> findByUser(String userName, Sort sort);

    @Aggregation(pipeline = {"{ '$addFields': { 'dateString': { $dateToString: { format: '%Y-%m-%d', date: '$createDate'} } }  }",
            "{ $match :{ 'status':'PAID','dateString' :{ '$gte' :?0 ,'$lte' :?1 }}}",
            "{ '$group':{ '_id' :$carID ,'count' :{ $count:{} } , 'total':{ '$sum': $total }}}",
            "{ '$sort' :{ 'count' :-1 } }",
            "{ '$limit' :5 }"})
    List<GroupResponse> billCarCountFromToDate(String from, String to);

    @Aggregation(pipeline = {"{ '$addFields': { 'dateString': { $dateToString: { format: '%Y-%m-%d', date: '$createDate'} } }  }",
            "{ $match :{ 'status':'PAID','dateString' :{ '$gte' :?0 ,'$lte' :?1 }}}",
            "{ '$group':{ '_id' :$customerID ,'count' :{ $count:{} } , 'total':{ '$sum': $total }}}",
            "{ '$sort' :{ 'count' :-1 } }",
            "{ '$limit' :5 }"})
    List<GroupResponse> billUserCountFromToDate(String from, String to);

    @Aggregation(pipeline = {"{ '$addFields': { 'dateString': { $dateToString: { format: '%Y-%m-%d', date: '$createDate'} } }  }",
            "{ $match :{ 'status':'PAID','dateString' :{ '$gte' :?0 ,'$lte' :?1 }}}",
            "{ '$group':{ '_id' :$customerID ,'count' : { '$sum': $total }, 'total':{ '$sum': $total }}}",
            "{ '$sort' :{ 'count' :-1 } }",
            "{ '$limit' :5 }"})
    List<GroupResponse> billUserTotalCountFromToDate(String from, String to);

    @Aggregation(pipeline = {
            "{ $match :{ 'status':'PAID'}}",
            "{ '$group':{ '_id' : null ,'count' :{ $count:{} }, 'total':{ '$sum': $total }}}",
    })
    GroupResponse billCount();

    @Aggregation(pipeline = {"{ '$addFields': { 'dateString': { $dateToString: { format: '%Y-%m-%d', date: '$createDate'} } }  }",
            "{ $match :{ 'status':'PAID' ,'dateString' :{ '$gte' :'?0-01-01' ,'$lte' :'?0-12-31' }}}",
            "{ '$group':{ '_id' :{$month:'$createDate'} ,'count' : { $count:{} }, 'total':{ '$sum': $total }}}"})
    List<GroupResponse> billTotalMonthInYear(String year);
    @Aggregation(pipeline = {"{ '$addFields': { 'dateString': { $dateToString: { format: '%Y-%m-%d', date: '$createDate'} } }  }",
            "{ $match :{ 'status':'PAID','dateString' :{ '$gte' :?0 ,'$lte' :?1 }}}",
            "{ '$group':{ '_id' : $dateString , 'count' : { $count:{}}, 'total':{ '$sum': $total }}}",
            "{ '$sort' :{ 'createDate' :-1 } }"})
    List<GroupResponse> billCountLast7DaysFrom(String from, String to);

}
