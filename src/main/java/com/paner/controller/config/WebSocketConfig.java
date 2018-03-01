package com.paner.controller.config;

import com.paner.controller.handler.WebSocketHander;
import com.paner.controller.interceptor.HandInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * Created by www-data on 16/5/27.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new WebSocketHander(),"/echo").addInterceptors(new HandInterceptor()).setAllowedOrigins("http://dev.elenet.me"); //支持websocket 的访问链接
       // webSocketHandlerRegistry.addHandler(new WebSocketHander(),"/sockjs/echo").addInterceptors(new HandInterceptor()).withSockJS(); //不支持websocket的访问链接

    }
}
