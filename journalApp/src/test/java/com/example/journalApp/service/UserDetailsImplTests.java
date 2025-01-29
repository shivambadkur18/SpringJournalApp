package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService ;

    @Mock
    private UserRepo userRepo ;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("ram").roles(new ArrayList<>()).build()) ;
        UserDetails user=  userDetailsService.loadUserByUsername("ram") ;
        Assertions.assertNotNull(user);
    }
}
