package com.learn.wesocketapp.controller;

import com.learn.wesocketapp.model.ChatMessage;
import com.learn.wesocketapp.model.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
public class MessageController {

    private SimpMessagingTemplate simpMessagingTemplate;


    public MessageController(SimpMessagingTemplate simpMessagingTemplate){

        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // to handle the message sent to the /hello endpoint
    // app/hello is the mappint where the sever will listening the message from stomp client
    // topic/messages is the place is the channel where the clinet is subscribe to listen to
//    @MessageMapping("/hello")
//    @SendTo("/topic/messages")
//    public String send(String username) {
//
//        return "hello " + username + "i am testing the web socket ";
//    }

    // to send the message to specific user

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sh,  @Payload String username){
        // this is the user name of the persion who is sending message to the specified user in payload
        String message1 = "hello from " + sh.getUser().getName();
        simpMessagingTemplate.convertAndSendToUser(username, "/queue/messages", message1);
    }


    // todo: learn to send message with payload
//    @PostMapping("/send/message")
//    public String sendMessage(@RequestBody MessageDto messageDto){
//
//        simpMessagingTemplate.convertAndSendToUser(messageDto.getUsername(), "queue/messages", messageDto.getMessage());
//        return "Successfully send";
//    }


}
