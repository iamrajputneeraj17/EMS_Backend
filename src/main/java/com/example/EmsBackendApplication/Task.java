package com.example.EmsBackendApplication;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class Task {
    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void task()
    {
        log.info("Task 1: Current Time"+ LocalDateTime.now());
    }
}
