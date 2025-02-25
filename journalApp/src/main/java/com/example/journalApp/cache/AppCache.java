package com.example.journalApp.cache;
import com.example.journalApp.entity.ConfigJournalAppEntity;
import com.example.journalApp.repo.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo ;

    public enum keys{
        WEATHER_API ;
    }

    public Map<String , String > appCacheMap ;

    @PostConstruct
    public void init(){
        appCacheMap = new HashMap<>();
        List<ConfigJournalAppEntity> all =  configJournalAppRepo.findAll();
        for (ConfigJournalAppEntity configJournalEntity : all) {
            appCacheMap.put(configJournalEntity.getKey() ,configJournalEntity.getValue()) ;
        }
    }
}
