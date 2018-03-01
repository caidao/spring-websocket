package com.paner.controller.handler;

import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by www-data on 16/5/27.
 */
public class WebSocketHander  implements WebSocketHandler{


    private static final ArrayList<WebSocketSession> users = new ArrayList<>();
    private int i=0;

    public WebSocketHander(){
//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(0);
//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                sendMessage();
//            }
//        },0,5, TimeUnit.SECONDS);
    }

    private void sendMessage()
    {
         for (int j=0;j<users.size();j++){
             WebSocketSession user=users.get(j);
             try
             {
             if (user.isOpen()){
                 TextMessage msg =new TextMessage(String.valueOf(i++)+" to "+user.getId());
                 user.sendMessage(msg);
             }}catch (Exception ex){
                 ex.printStackTrace();
             }
             System.out.println("sendMessage "+ user.toString() + ",thred id="+Thread.currentThread().getName());
         }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("链接成功......");
        users.add(webSocketSession);
        String userName = (String) webSocketSession.getAttributes().get("WEBSOCKET_USERNAME");
        if(userName!= null){
            //查询未读消息
            int count = 5;
            webSocketSession.sendMessage(new TextMessage(count + ""));
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        //sendMessageToUsers(new TextMessage(webSocketMessage.getPayload() + ""));
        TextMessage message = new TextMessage(webSocketMessage.getPayload()+"");
        webSocketSession.sendMessage(message);
        System.out.println("handleMessage " + i);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        System.out.println("链接出错，关闭链接......");
        users.remove(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("链接关闭......" + closeStatus.toString());
        users.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    TextMessage msg =new TextMessage(message.getPayload()+" to "+user.getId());
                    user.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


}
