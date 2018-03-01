package com.paner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

/**
 * Created by www-data on 16/5/29.
 */
@Controller
public class GreetingController {


    public SimpMessagingTemplate brokerMessagingTemplate;

    @Autowired
    public GreetingController(SimpMessagingTemplate brokerMessagingTemplate) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
    }

    @MessageMapping("/greeting")
    @SendTo("/topic/greetings")
    public String HandleMessae(String message) throws InterruptedException {

        Thread.sleep(3000);
        brokerMessagingTemplate.convertAndSend("/topic/greetings","{\"name\":\"test\"}");
        return  message;
    }

    


}
