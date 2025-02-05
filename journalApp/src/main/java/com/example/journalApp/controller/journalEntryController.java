package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.entity.journalEntry;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    private JournalEntryService journalEntryService ;

    @Autowired
    private UserService userService ;


   /* @GetMapping{"/GetAll"}
    public ResponseEntity<?> getAll(){

        


        return new ResponseEntity<>(HttpStatus.OK) ;
    }*/


    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;
        User user = userService.findByUserByName(userName) ;
        List<journalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK) ;
        }
        return new ResponseEntity<>( userName , HttpStatus.NOT_FOUND) ;
    }
    
    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry ){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName() ;
            myEntry.setDate(LocalDateTime.now());
            User user = userService.findByUserByName(userName) ;
             journalEntryService.saveEntry(myEntry , userName) ;
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED) ;
        }
        catch (Exception e ){
            return new ResponseEntity<>(myEntry , HttpStatus.BAD_REQUEST) ;
        }
    }

    @GetMapping("/id/{prodId}")
    public ResponseEntity<journalEntry> getIndividual(@PathVariable ObjectId prodId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserByName(userName);
        List<journalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(prodId)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<journalEntry> j = journalEntryService.getIndividualById(prodId);
            if (j.isPresent()) {
                return new ResponseEntity<>(j.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id/{prodId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId prodId ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(prodId , userName) ;
        if(removed) return new ResponseEntity<>(prodId ,HttpStatus.OK) ;
        else return new ResponseEntity<>(prodId , HttpStatus.NOT_FOUND) ;
    }


    @PutMapping("id/{prodId}")
    public ResponseEntity<?> update(@PathVariable ObjectId prodId , @RequestBody journalEntry myEntry){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserByName(userName) ;
        List<journalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(prodId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {

            Optional<journalEntry> journalEntry = journalEntryService.getIndividualById(prodId);
            if (journalEntry.isPresent()) {
                journalEntry old = journalEntry.get() ;
                old.setTitle(myEntry.getTitle() != null &&  !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
                old.setContent(myEntry.getContent() != null &&  !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old) ;
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }


        journalEntry old = journalEntryService.getIndividualById(prodId).orElse(null) ;
        if (old != null) {
            old.setTitle(myEntry.getTitle() != null &&  !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null &&  !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old) ;
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
    }
}
