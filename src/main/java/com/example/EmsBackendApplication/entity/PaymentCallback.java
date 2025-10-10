package com.example.EmsBackendApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "payment_callback")
public class PaymentCallback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentReferenceNo;
    private String customerAppRefNumber;
    private LocalDateTime paymentDatetime;
    private String paymentTransactionStatus;
    private String failureReason;
    private String clientID;
    private String policyNumber;
    private String amount;
}
