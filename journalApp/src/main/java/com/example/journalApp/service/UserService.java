package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo ;

    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void saveEntry(User m) {
        userRepo.save(m) ;
    }

    public void createUser(User u ) {
       u.setPassword(passwordEncoder.encode(u.getPassword()));
      //  u.setPassword(u.getPassword());
        u.setRoles(Arrays.asList("USER"));
        userRepo.save(u);
    }



    public Optional<User> getIndividualById(ObjectId prodId) {
        return userRepo.findById(prodId);
    }

    public void deleteById(ObjectId prodId) {
        userRepo.deleteById(prodId);
    }

   /* public void deleteByuserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
    }*/

    public User findByUserByName(String userName){
        return userRepo.findByUserName(userName) ;
    }



}
