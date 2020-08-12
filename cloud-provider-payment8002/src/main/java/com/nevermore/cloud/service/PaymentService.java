package com.nevermore.cloud.service;

import com.nevermore.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById( Long id);
}
