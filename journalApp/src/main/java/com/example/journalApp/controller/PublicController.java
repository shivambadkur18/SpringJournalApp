package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService ;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User u ) {
        userService.createUser(u) ;
        return new ResponseEntity<>(u.getUserName(), HttpStatus.CREATED);
    }
}
