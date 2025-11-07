package com.example.EmsBackendApplication.controller;

import com.example.EmsBackendApplication.DTO.PaymentCallbackDTO;
import com.example.EmsBackendApplication.DTO.PaymentCallbackSearchDTO;
import com.example.EmsBackendApplication.service.PaymentCallbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

;import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment-callback")
@Slf4j
public class PaymentCallbackController {

    @Autowired
    private PaymentCallbackService callbackService;

    @Autowired
    private ObjectMapper mapper;




    @PostMapping("/saveCall")
    public ResponseEntity<String> receivePaymentCallback(@RequestBody PaymentCallbackDTO dto) {
        try {
            log.info("Received payment callback: {}", dto);
            callbackService.saveCallback(dto);
            return ResponseEntity.ok("Callback received and processed successfully.");

        } catch (Exception e) {
            log.error("Error processing callback", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing callback.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PaymentCallbackDTO> searchCallback(@RequestBody PaymentCallbackSearchDTO dto) {
        PaymentCallbackDTO result = callbackService.searchCallback(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/jsonProcess")
    public ResponseEntity<String> jsonProcess(@RequestBody JsonNode node) throws JsonProcessingException {
        String username = node.path("user").path("userName").asText();
        int age = node.path("user").path("age").asInt();
        System.out.println(mapper.writeValueAsString(node));
        return ResponseEntity.ok("UserName:" + username + "Age: "+ age);
    }
}
