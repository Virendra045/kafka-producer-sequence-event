package com.example.kafkasequencemessageproducer.controller;

import com.example.kafkasequencemessageproducer.config.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/app")
@Slf4j
public class AppController {

    @Autowired
    private SendMessage sendMessage;

    @GetMapping
    public Mono<String> start(){
        log.info("started");
       return sendMessage.sendEvent();
    }


}
