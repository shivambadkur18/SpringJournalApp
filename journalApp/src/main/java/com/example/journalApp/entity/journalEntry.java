package com.example.journalApp.entity;

import com.example.journalApp.enums.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class journalEntry {
    @Id
    private ObjectId id ;
    @NonNull
    private String title ;
    private String content ;
    private LocalDateTime date ;
    private Sentiment sentiment ;



}
