package com.example.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {
                                          // pojo class for weather response
    private Current current;

    @Getter
    @Setter
    public class Current{

        private int temperature;
        @JsonProperty("weather_descriptions")
        private  List<String> weatherDescriptions;

        private int feelslike;

    }

 /*   public class Location{
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezone_id;
        private String localtime;
        private int localtime_epoch;
        private String utc_offset;
    }

    public class Request{
        private String type;
        private String query;
        private String language;
        private String unit;
    }
*/
}



