package com.example.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id ;

    @Indexed(unique = true)
    @NonNull
    private String userName ;
    @NonNull
    private String password ;

    @DBRef
    private List<journalEntry> journalEntries = new ArrayList<>() ;
    private List<String> roles  ;


}
