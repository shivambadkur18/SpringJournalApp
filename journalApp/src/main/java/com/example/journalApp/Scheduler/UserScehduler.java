package com.example.journalApp.Scheduler;

import com.example.journalApp.cache.AppCache;
import com.example.journalApp.entity.User;
import com.example.journalApp.entity.journalEntry;
import com.example.journalApp.enums.Sentiment;
import com.example.journalApp.repo.UserRepoImpl;
import com.example.journalApp.service.EmailService;
import com.example.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScehduler {

    @Autowired
    private EmailService emailService ;

    @Autowired
    private UserRepoImpl userRepoImpl ;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService ;

    @Autowired
    private AppCache appCache ;


    @Scheduled(cron = "0 30 09 * * MON")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepoImpl.getUserForSentiAn();
        for (User user : users) {
            List<journalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7 , ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment , Integer> sentiCounts = new HashMap<>() ;
            for (Sentiment sentiment : sentiments) {
                if(sentiment!= null)
                    sentiCounts.put(sentiment , sentiCounts.getOrDefault(sentiment , 0 )+ 1 );
            }
            Sentiment mostFrequentSenti = null ;
            int maxCount = 0 ;
            for(Map.Entry<Sentiment , Integer > entry : sentiCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue() ;
                    mostFrequentSenti = entry.getKey();
                }
            }
            if(mostFrequentSenti != null){
                emailService.sendMail(user.getEmail() , "Sentiment for last 7 days " , mostFrequentSenti.toString());
            }
        }
    }
    @Scheduled(cron = "0 0 * * *") // EveryDay at 12 AM
    public void refreshAppCache(){
        appCache.init();
    }
}
