package com.learn.wesocketapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // this is end point where the client and server create connection i.e hanshake
        registry.addEndpoint("/hello")
                // this will create fall back i.e. goto http protocol if the stomp protocol not work
                // if the broswer donot support the websocket the go to xhr
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

//        // is the endpoint where the client will listen for messages
//        registry.enableSimpleBroker("/topic");

//        registry.enableSimpleBroker("queue");
//
//        // this is the endpoint where the client will sent message to server
//        registry.setApplicationDestinationPrefixes("/app");
//
//        // this is prefix to send to the specifig user default prefix is /user
//        registry.setUserDestinationPrefix("/users");

        registry.enableSimpleBroker("queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(new UserInterceptor());
    }
}
