package com.ctse.userservice.repository;


import com.ctse.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'userRole':{$in:?0}}")
    List<User> findByUserRole(String status[]);

}
