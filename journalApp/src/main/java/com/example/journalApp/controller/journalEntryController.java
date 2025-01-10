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


@Component
@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    private JournalEntryService journalEntryService ;

    @Autowired
    private UserService userService ;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;
        User user = userService.findByUserByName(userName) ;
        List<journalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK) ;
        }
        return new ResponseEntity<>( userName , HttpStatus.NO_CONTENT) ;
    }
    
    @PostMapping("/{userName}")
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry , @PathVariable String userName){
        try {
            myEntry.setDate(LocalDateTime.now());
            User user = userService.findByUserByName(userName) ;
            journalEntryService.saveEntry(myEntry , userName) ;
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED) ;
        }
        catch (Exception e ){
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED) ;
        }
    }

    @GetMapping("/id/{prodId}")
    public ResponseEntity<journalEntry> getIndividual(@PathVariable ObjectId prodId){
        Optional<journalEntry> j = journalEntryService.getIndividualById(prodId) ;
        if (j.isPresent()){
            return new ResponseEntity<>(j.get() , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
    }


    @DeleteMapping("id/{userName}/{prodId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId prodId , @PathVariable String userName){
        journalEntryService.deleteById(prodId , userName) ;
        return new ResponseEntity<>(prodId ,HttpStatus.OK) ;
    }
    @PutMapping("id/{userName}/{prodId}")
    public ResponseEntity<?> update(@PathVariable ObjectId prodId ,
                                    @RequestBody journalEntry myEntry ,
                                    @PathVariable String userName){

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
