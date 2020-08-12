package com.nevermore.cloud.controller;

import com.nevermore.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfoOk(id);
        log.info("**************result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("**************result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/circuit/{id}")
    public String paymentInfoCircuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("**************result:" + result);
        return result;
    }

}
