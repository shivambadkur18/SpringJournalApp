package com.example.journalApp.ControllerTests;

import com.example.journalApp.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController ;

    @Test
    public void greetingTests(){
        userController.greetings();

    }
}
