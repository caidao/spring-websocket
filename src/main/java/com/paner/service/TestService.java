package com.paner.service;

import com.paner.dao.mapper.TestMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by www-data on 16/5/26.
 */

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public String test(){
        return testMapper.getUser(45133);
       // return "Welcome to websocket test!";
    }


}
