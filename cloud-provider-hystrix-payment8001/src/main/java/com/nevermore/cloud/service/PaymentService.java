package com.nevermore.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentService {

    public String paymentInfoOk(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoOk "+id+"\tO(∩_∩)O哈哈~";
    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public String paymentInfoTimeOut(Integer id){
        int age = 1/0;
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfoTimeOut "+id+"\t耗时3秒钟";
    }

    public String paymentInfoTimeOutHandler(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " o(╥﹏╥)o";
    }

    //=========================================================================================================

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
   public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"流水号 "+serialNumber;
   }

    public String paymentCircuitBreakerFallBack(@PathVariable("id") Integer id){
        return "id不能是负数 "+ id;
    }
}
