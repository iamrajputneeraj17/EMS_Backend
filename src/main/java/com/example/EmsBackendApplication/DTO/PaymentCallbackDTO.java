package com.example.EmsBackendApplication.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
public class PaymentCallbackDTO {
    private String paymentReferenceNo;
    private String customerAppRefNumber;
    private LocalDateTime paymentDatetime;
    private String paymentTransactionStatus;
    private String failureReason;
    private String clientID;
    private String policyNumber;
    private String amount;
}