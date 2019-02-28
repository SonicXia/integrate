package com.atsonic.integrate.modules.moduleA.service;

import com.atsonic.integrate.modules.moduleA.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
public class BookService {

//    @RabbitListener(queues = "atguigu.news") // 被监听的queue中消息会被实时消费掉
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

//    @RabbitListener(queues = "atguigu") // 被监听的queue中消息会被实时消费掉
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}