package com.cg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.PaymentDTO;
import com.cg.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/revenue/datewise")
    public ResponseEntity<List<PaymentDTO>> getPaymentRevenueByDate() {
        logger.info("Received request for date-wise payment revenue");
        
        List<PaymentDTO> revenueList = paymentService.getRevenueByDate();
        
        if (revenueList.isEmpty()) {
            logger.warn("No payment data found");
            return ResponseEntity.noContent().build();
        }
        
        logger.info("Returning {} payment records", revenueList.size());
        return ResponseEntity.ok(revenueList);
    }
}