package com.cg.service;
 
import java.util.List;
import com.cg.model.Payment;

 
public interface PaymentService {
    List<Payment> getRevenueByDate() throws Exception;
}