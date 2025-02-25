package com.example.journalApp.RepoTests;

import com.example.journalApp.repo.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepoimpl ;

    @Test
    public void testNewUser(){
        userRepoimpl.getUserForSentiAn();
    }

}
