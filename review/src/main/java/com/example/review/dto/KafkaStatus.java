package com.example.review.dto;

public record KafkaStatus<T>(
        T data, String status
) {
}