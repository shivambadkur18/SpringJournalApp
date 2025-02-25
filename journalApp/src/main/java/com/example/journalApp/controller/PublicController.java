package com.example.journalApp.controller;

import com.example.journalApp.Utils.JwtUtil;
import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserDetailsServiceImpl;
import com.example.journalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private UserDetailsServiceImpl userDetailsService ;

    @Autowired
    private UserService userService ;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User u ) {
        userService.createUser(u) ;
        return new ResponseEntity<>(u.getUserName(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User u ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(u.getUserName() , u.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(u.getUserName()) ;
            String jwt =  jwtUtil.generateToken(u.getUserName()) ;
            return new ResponseEntity<>(jwt , HttpStatus.OK) ;
        }
        catch (Exception e ){
            log.error("exception occured while creating Authentication Token  " , e );
            return new ResponseEntity<>("Incorret username/passsword" , HttpStatus.BAD_REQUEST) ;

        }

    }

}
