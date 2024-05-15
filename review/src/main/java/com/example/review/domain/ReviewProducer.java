package com.example.review.domain;

import com.example.review.dto.KafkaStatus;
import com.example.review.dto.request.KafkaRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewProducer {
    private final KafkaTemplate<String, KafkaStatus<KafkaRequest>> kafkaTemplate;
    @Value("${kafka.product.name}") private String name;

    @Bean
    private NewTopic newTopic(){
        return new NewTopic(name, 1 ,(short)1);
    }
    public void send(KafkaRequest kafkaRequest, String status){
        KafkaStatus<KafkaRequest> kafkaStatus = new KafkaStatus<>(kafkaRequest, status);
        kafkaTemplate.send(name, kafkaStatus);
    }
}
