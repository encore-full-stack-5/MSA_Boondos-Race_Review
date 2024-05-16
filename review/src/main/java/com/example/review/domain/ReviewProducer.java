package com.example.review.domain;

import com.example.review.dto.KafkaStatus;
import com.example.review.dto.request.KafkaRequest;
import com.example.review.dto.request.ProductKafkaRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewProducer {
//    private final KafkaTemplate<String, KafkaStatus<KafkaRequest>> kafkaTemplate;
    private final KafkaTemplate<Object, KafkaRequest> kafkaTemplate;
    private final KafkaTemplate<Object, ProductKafkaRequest> productKafkaTemplate;
//    private final KafkaTemplate<String ,KafkaStatus<?>> kafkaRequestKafkaTemplate;

//확실하게 보내는게 좋다 메서드를 만들어서 빼는 것 보다
    @Bean
    private NewTopic order(){
        return new NewTopic("review-topic", 1 ,(short)1);
    }
    public void productSend(ProductKafkaRequest request){
//        KafkaStatus<KafkaRequest> kafkaStatus = new KafkaStatus<>(kafkaRequest, status);
        productKafkaTemplate.send("review-topic", request);
    }

    @Bean
    private NewTopic product(){
        return new NewTopic("review-topic", 1 ,(short)1);
    }
    public void send(KafkaRequest kafkaRequest){
//        KafkaStatus<KafkaRequest> kafkaStatus = new KafkaStatus<>(kafkaRequest, status);
        kafkaTemplate.send("product-review-topic", kafkaRequest);
    }

//    public <T> void sendResponse(T data ,String status,String topic){
//        kafkaTemplate.send(topic , new KafkaResponse<>(data, status));
//    }
}
