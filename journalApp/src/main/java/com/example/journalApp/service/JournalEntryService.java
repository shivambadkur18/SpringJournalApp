package com.example.journalApp.service;
import com.example.journalApp.entity.User;
import com.example.journalApp.repo.JournalRepo;
import com.example.journalApp.entity.journalEntry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@RestController
@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalRepo journalRepo ;

    @Autowired
    UserService userService ;


    public List<journalEntry> getAll() {
        return journalRepo.findAll();
    }

    @Transactional
    public void saveEntry(journalEntry m, String userName) {
        try {
            User user = userService.findByUserByName(userName) ;
            journalEntry saved = journalRepo.save(m) ;
            user.getJournalEntries().add(saved );
            userService.saveEntry(user);
        }
        catch (Exception e ){
            System.out.println(e);
            throw new RuntimeException("an error occured while saving entry." , e ) ;
        }
    }

    public void saveEntry(journalEntry m) {
        journalRepo.save(m);
    }
    public Optional<journalEntry> getIndividualById(ObjectId prodId) {
        return journalRepo.findById(prodId);
    }

    @Transactional
    public boolean deleteById(ObjectId prodId, String userName) {
        boolean removed = false ;
        try {
            User user = userService.findByUserByName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(prodId));
            if (removed) {
                userService.saveEntry(user);
                journalRepo.deleteById(prodId);
            }
        }
        catch (Exception e ){
           log.error("error");
            throw new RuntimeException("An errror occured while deleting the entry ");
        }
        return removed ;
    }





}
