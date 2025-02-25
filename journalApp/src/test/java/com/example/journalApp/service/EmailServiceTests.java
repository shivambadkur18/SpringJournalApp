package com.example.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService ;

    @Test
    public void semdMail(){
       /* List<String> names = new ArrayList<>() ;
        names.add("shivambadkur58@gmail.com") ;
        names.add("shvmbadkur@gmail.com") ;
        names.add("at8361341@gmail.com") ;
        names.add("shivamadkur588@gmail.com") ;*/

        String sub = "regarding mailCheck" ;
        String body = "this isdddddddddddddddddddddd an email from springboot to the user for checking the service " ;
        emailService.sendMail("shivambadkur58@gmail.com", sub , body);
    }



}
