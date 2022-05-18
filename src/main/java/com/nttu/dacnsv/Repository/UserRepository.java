package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Request.GroupResponse;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName (String userID);
    @Query("{'role.id':'?0'}}")
    List<User> findUserByRoleId (String id);
    @Query("{'role.roleName':'?0'}")
    List<User> findUserByRoleName (String roleName);
    @Query("{'userName':'?0','password':'?1'}")
    Optional<User> findByUserNameAndPassword(String userName, String password);
    @Aggregation(pipeline = {
            "{ '$group':{ '_id' : null ,'count' :{ $count:{} }, 'total':{ '$sum': $total }}}"
    })
    GroupResponse userCount();
}
