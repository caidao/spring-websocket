package com.paner.controller;

import com.paner.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by www-data on 16/5/26.
 */
@RestController
public class TestController {


    @Resource
    private TestService testService;

    @RequestMapping(value = "/test")
    public String test(){
        System.out.println("welcome to web-scoket!");
         return "test message";
    }
}
