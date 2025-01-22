package com.cg.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Payment;
import com.cg.service.PaymentService;
 
@RestController
@RequestMapping("/homePage/dashboard/paymentManagement")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    // Get all date-wise revenue
    @GetMapping("/all-revenue")
    @ResponseBody
    public ResponseEntity<List<Payment>> getRevenueByDate() {
        try {
            List<Payment> payments = paymentService.getRevenueByDate();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

}