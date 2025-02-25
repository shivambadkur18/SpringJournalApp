package com.example.journalApp.ControllerTests;


import com.example.journalApp.controller.PublicController;
import com.example.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class publicContollerTests {

    @Autowired
    private PublicController publicController ;

    @Test
    public void loginTests(){
        ResponseEntity<?> rs  =  publicController.login(dummyUser()) ;
        System.out.println(rs);
    }
    public User dummyUser(){
        User u = new User() ;
        u.setUserName("Shivam");
        u.setPassword("Shivam");
        return u ;
    }

}
