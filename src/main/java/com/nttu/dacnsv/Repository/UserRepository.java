package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.User;
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
}
