package com.example.EmsBackendApplication.service;

import com.example.EmsBackendApplication.DTO.PaymentCallbackDTO;
import com.example.EmsBackendApplication.DTO.PaymentCallbackSearchDTO;
import com.example.EmsBackendApplication.entity.PaymentCallback;
import com.example.EmsBackendApplication.repository.PaymentCallbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//import javax.persistence.EntityNotFoundException;
//import java.time.LocalDateTime;

@Slf4j
@Service
public class PaymentCallbackService {
    @Autowired
    private PaymentCallbackRepository callbackRepository;

    public void saveCallback(PaymentCallbackDTO dto) {
        PaymentCallback callback = new PaymentCallback();
        callback.setPaymentReferenceNo(dto.getPaymentReferenceNo());
        callback.setCustomerAppRefNumber(dto.getCustomerAppRefNumber());
        callback.setPaymentDatetime(dto.getPaymentDatetime());
        callback.setPaymentTransactionStatus(dto.getPaymentTransactionStatus());
        callback.setFailureReason(dto.getFailureReason());
        callback.setClientID(dto.getClientID());
        callback.setPolicyNumber(dto.getPolicyNumber());
        callback.setAmount(dto.getAmount());

        PaymentCallback savedCallback = callbackRepository.save(callback);
        log.info("Saved payment callback: {}", savedCallback);

    }

    public PaymentCallbackDTO searchCallback(PaymentCallbackSearchDTO dto) {
        LocalDateTime start = dto.getPaymentDatetime()
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        log.info(String.valueOf(start));

        PaymentCallback callback = callbackRepository
                .findFirstByPaymentDatetimeGreaterThanEqualAndClientIDAndAmountAndPolicyNumber(
                        start,
                        dto.getClientID(),
                        dto.getAmount(),
                        dto.getPolicyNumber()
                ).orElseThrow(() -> new EntityNotFoundException("Data not found!"));

        PaymentCallbackDTO responseDto = new PaymentCallbackDTO();
        responseDto.setPaymentReferenceNo(callback.getPaymentReferenceNo());
        responseDto.setCustomerAppRefNumber(callback.getCustomerAppRefNumber());
        responseDto.setPaymentDatetime(callback.getPaymentDatetime());
        responseDto.setPaymentTransactionStatus(callback.getPaymentTransactionStatus());
        responseDto.setFailureReason(callback.getFailureReason());
        responseDto.setClientID(callback.getClientID());
        responseDto.setPolicyNumber(callback.getPolicyNumber());
        responseDto.setAmount(callback.getAmount());

        log.info("Resonse", responseDto);
        return responseDto;
    }
}





