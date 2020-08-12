package com.nevermore.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "---------------paymentfallback paymentInfoOk.......";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "---------------paymentfallback paymentInfoTimeout.......";
    }
}
