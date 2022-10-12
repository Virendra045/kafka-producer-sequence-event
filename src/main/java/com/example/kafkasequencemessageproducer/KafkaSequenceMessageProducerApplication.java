package com.example.kafkasequencemessageproducer;

import com.example.kafkasequencemessageproducer.config.SendMessage;
import com.example.kafkasequencemessageproducer.model.Trade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class KafkaSequenceMessageProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSequenceMessageProducerApplication.class, args);
	}
}
