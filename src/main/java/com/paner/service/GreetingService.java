package com.paner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by www-data on 16/6/1.
 */
@Service
public class GreetingService {

//    public SimpMessagingTemplate brokerMessagingTemplate;
//
//    @Autowired
//    public GreetingService(SimpMessagingTemplate template) {
//        this.brokerMessagingTemplate = template;
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void timerSend(){
//        System.out.println("timerSend.....");
//        brokerMessagingTemplate.convertAndSend("/topic/greetings","{\"name\":\"test\"}");
//    }

}
