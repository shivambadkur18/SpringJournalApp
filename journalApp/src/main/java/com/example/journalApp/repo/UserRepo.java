package com.example.journalApp.repo;


import com.example.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
     User findByUserName(String username) ;

     void deleteByUserName(String name);
}
