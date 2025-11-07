package com.example.EmsBackendApplication.controller;


import com.example.EmsBackendApplication.entity.ConfigurationPropertiesTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profileTesting/api")
public class ProfileTest {
    @Autowired
    private ConfigurationPropertiesTest propertiesTest;

    @Value("${username1}")
    String username;


    @Value("${password}")
    String password;


    @GetMapping("/uat")
    public String getUat()
    {
        String url = propertiesTest.getUrl();
        return "username:" + username + "password: " + password + "url: "+ url;
    }
}
