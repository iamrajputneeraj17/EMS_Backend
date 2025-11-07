package com.example.EmsBackendApplication.controller;


import com.example.EmsBackendApplication.entity.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherAPI_restTemplatePractice {

    @Autowired
    private WeatherService service;

    @GetMapping("/data")
    public ResponseEntity<String> weatherResponse()
    {
        return ResponseEntity.ok(service.getWeatherData("Mathura"));
    }
}
