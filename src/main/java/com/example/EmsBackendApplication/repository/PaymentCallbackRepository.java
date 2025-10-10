package com.example.EmsBackendApplication.repository;

import com.example.EmsBackendApplication.entity.PaymentCallback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentCallbackRepository extends JpaRepository<PaymentCallback, Long> {
    Optional<PaymentCallback> findFirstByPaymentDatetimeGreaterThanEqualAndClientIDAndAmountAndPolicyNumber(
            LocalDateTime start,
            String clientID,
            String amount,
            String policyNumber
    );
}
