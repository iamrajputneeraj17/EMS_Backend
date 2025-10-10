package com.example.EmsBackendApplication.controller;

import com.example.EmsBackendApplication.DTO.PaymentCallbackDTO;
import com.example.EmsBackendApplication.DTO.PaymentCallbackSearchDTO;
import com.example.EmsBackendApplication.service.PaymentCallbackService;
import lombok.extern.slf4j.Slf4j;
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
}
