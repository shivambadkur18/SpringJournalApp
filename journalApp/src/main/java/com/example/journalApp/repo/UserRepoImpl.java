package com.example.journalApp.repo;
import com.example.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate ;


    public List<User> getUserForSentiAn(){
        Query query = new Query() ;
        //query.addCriteria(Criteria.where("userName").is("rAm")) ; // this is for single criteria
       // query.addCriteria(Criteria.where("email").ne(null).ne("")) ;
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) ;
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true)) ;
        List<User> users =  mongoTemplate.find(query ,User.class) ;
        return users ;
    }
}

 /*
       Criteria criteria = new Criteria() ; // using and operator means both should be true
        query.addCriteria(criteria.andOperator(
                (Criteria.where("userName").is("rAm")),
                (Criteria.where("sentimentAnalysis").exists(true))
        ));
        */
