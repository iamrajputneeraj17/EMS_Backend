package com.example.EmsBackendApplication;


import com.example.EmsBackendApplication.JuitTesting.VivoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String k, Class<T> entityClass)
    {
       try {
           Object o = redisTemplate.opsForValue().get(k);
//           redisTemplate.delete(k);
           ObjectMapper objectMapper = new ObjectMapper();
           return objectMapper.readValue(o.toString(), entityClass);
       }
       catch (Exception e)
       {
           log.error("Exception ",e);
           return null;
       }
    }

    // ttl --> Time to live
    public void set(String k, Object o, Long ttl)
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(k, jsonValue, ttl, TimeUnit.MINUTES);
        }
        catch (Exception e)
        {
            log.error("Exception ",e);
        }
    }
}
