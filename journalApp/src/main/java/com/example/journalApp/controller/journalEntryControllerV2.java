/*
package com.example.journalApp.controller;

import com.example.journalApp.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
@RequestMapping("/_journal")
public class journalEntryControllerV2 {

    @Autowired
    private Service service ;



    @GetMapping
    public List<journalEntry> getAll(){

    }
    @PostMapping
    public void createEntry(@RequestBody journalEntry myEntry){
        repo.save()


    }
    @GetMapping("id/{prodId}")
    public journalEntry getJournqlEntryById(@PathVariable long prodId){


    }
    @DeleteMapping("id/{prodId}")
    public journalEntry deleteById(@PathVariable long prodId){

    }
    @PutMapping("id/{prodId}")
    public journalEntry update(@PathVariable long prodId , @RequestBody journalEntry myEntry){

    }

}
*/
