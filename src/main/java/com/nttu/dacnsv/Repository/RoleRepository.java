package com.nttu.dacnsv.Repository;

import com.nttu.dacnsv.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role , String> {
    @Query("{'roleName':?0}")
    Optional<Role> findByRoleName(String roleName);
    @Query("{'roleLevel':?0}")
    List<Role> findByLevel (int roleLevel);
}
