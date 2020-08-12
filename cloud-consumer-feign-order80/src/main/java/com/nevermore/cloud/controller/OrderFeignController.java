package com.nevermore.cloud.controller;

import com.nevermore.cloud.entities.CommonResult;
import com.nevermore.cloud.entities.Payment;
import com.nevermore.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getpaymentById(@PathVariable("id")Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
       return paymentFeignService.paymentFeignTimeOut();
    }

}
