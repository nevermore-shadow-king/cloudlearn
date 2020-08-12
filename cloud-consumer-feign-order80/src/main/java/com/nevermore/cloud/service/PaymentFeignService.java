package com.nevermore.cloud.service;

import com.nevermore.cloud.entities.CommonResult;
import com.nevermore.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id")Long id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeOut();
}
