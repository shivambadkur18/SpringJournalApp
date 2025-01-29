package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo ;

    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;


    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void saveEntry(User user)
    {
        userRepo.save(user) ;
    }

    public boolean createUser(User user ) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return true ;
        }
        catch (Exception e ){
            log.info("hahahahahahahahahahahahahahaha for {} : " , user.getUserName());
            log.error("chud gye guru ");
            return false;
        }
    }

    public Optional<User> getIndividualById(ObjectId prodId) {
        return userRepo.findById(prodId);
    }

    public void deleteById(ObjectId prodId) {
        userRepo.deleteById(prodId);
    }

    public User findByUserByName(String userName){
        return userRepo.findByUserName(userName) ;
    }


    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER" , "ADMIN"));
        userRepo.save(user) ;
    }
}
