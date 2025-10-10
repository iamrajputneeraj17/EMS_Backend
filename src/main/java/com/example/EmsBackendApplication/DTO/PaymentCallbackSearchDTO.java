package com.example.EmsBackendApplication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentCallbackSearchDTO {
    private String paymentReferenceNo;
    private String customerAppRefNumber;
    private LocalDateTime paymentDatetime;
    private String paymentTransactionStatus;
    private String failureReason;
    private String clientID;
    private String policyNumber;
    private String amount;
}
