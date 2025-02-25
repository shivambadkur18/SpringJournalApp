package com.example.journalApp.service;
import com.example.journalApp.api.response.WeatherResponse;
import com.example.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {

    @Value("${weather.api.key}")

    private String apiKey  ;//= "b1e7c7a70181477aec71ee4ea9ecf25a" ;

 // private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private AppCache appCache ;

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private RedisService redisService ;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null){
            return weatherResponse ;
        }
        else {
            String finalApi = appCache.appCacheMap.get(AppCache.keys.WEATHER_API).replace("<apiKey>" , apiKey ).replace("<city>" , city) ;
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi , HttpMethod.GET , null , WeatherResponse.class) ;
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("weather_of_" + city, body ,300l );
            }
            return body ;
        }

    }


}



