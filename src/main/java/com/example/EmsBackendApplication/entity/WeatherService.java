package com.example.EmsBackendApplication.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {
    @Value("${WEATHER_API_KEY}")
    public String API_KEY;
    public static final String URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate template;

    public String getWeatherData(String city) {
        String updatedUrl = URL.replace("CITY", city).replace("API_KEY", API_KEY);

        ResponseEntity<String> response = template.exchange(
                updatedUrl,
                HttpMethod.GET,
                null,
                String.class
        );

        // return only the body (actual data)
        return response.getBody();
    }

}
