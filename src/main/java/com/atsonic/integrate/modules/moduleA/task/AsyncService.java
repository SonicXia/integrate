package com.atsonic.integrate.modules.moduleA.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    //告诉Spring这是一个异步方法，需要开启@EnableAsync
    @Async
    public void hello(){
        try {
            System.out.println("开始等待...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步方法处理数据中...");
    }
}
