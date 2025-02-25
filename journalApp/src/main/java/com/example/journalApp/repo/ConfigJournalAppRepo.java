package com.example.journalApp.repo;


import com.example.journalApp.entity.ConfigJournalAppEntity;
import com.example.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
