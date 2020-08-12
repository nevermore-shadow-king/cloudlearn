package com.nevermore.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nevermore.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfoTimeFallBackGlobal")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
//    @HystrixCommand
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfoTimeout(id);
        return result;
    }

//    public String paymentInfoTimeOutHandler(Integer id){
//        return " o(╥﹏╥)o";
//    }

    //全局fallback
    public String paymentInfoTimeFallBackGlobal(Integer id){
        return "全局全局 o(╥﹏╥)o";
    }


}
