package com.example.kafkasequencemessageproducer.config;

import com.example.kafkasequencemessageproducer.model.Trade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@AllArgsConstructor
public class SendMessage {
    private StreamBridge streamBridge;
    public static final String EVENT_PRODUCER_BINDING_NANE = "producerServiceEvent-out-0";
    public void sendEvent(Trade trade, String key) {
        streamBridge.send(EVENT_PRODUCER_BINDING_NANE, MessageBuilder.withPayload(trade)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key)
                .build());
        log.info("Sent event for contract {} changing status to {}", trade);
    }

    public Mono<String> sendEvent(){
        Runnable request1 = () -> sendMessageInTopic( "Viru1234", 1, 5);
        Runnable request2 = () -> sendMessageInTopic("KumarSingh12311", 6, 10);
        Runnable request3 = () -> sendMessageInTopic("Test12325", 11, 15);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(request1);
        //executorService.submit(request2);
        //executorService.submit(request3);
        return Mono.just("test");
    }

    private  void sendMessageInTopic(String tokenKey, int start, int end) {
        for (int id = start; id <= end; id++) {
            Trade trade = Trade.builder().id(String.valueOf(id)).securityId(tokenKey).value("test data").build();
            try {
                String s = new ObjectMapper().writeValueAsString(trade);
                sendEvent(trade, trade.getSecurityId());
                System.out.println("msg : " + s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
