package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.repo.UserRepo;
import com.example.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService ;

    @Autowired
    private UserRepo userRepo ;


   /* @GetMapping   // no need to see all user only admin can see all the users and their journal entries
    public List<User> getAll(){
        return userService.getAll();
    }*/

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;
        User userInDb = userService.findByUserByName(userName) ;
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword    (user.getPassword());
            userService.createUser(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK) ;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.GONE) ;
    }

}
