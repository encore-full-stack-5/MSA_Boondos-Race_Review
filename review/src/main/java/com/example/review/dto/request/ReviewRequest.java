package com.example.review.dto.request;

import com.example.review.domain.Review;
import com.example.review.dto.response.TokenInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private int point;
    private String content;
    private UUID userId;


    public Review toEntity(TokenInfoResponse tokenInfoResponse){
        return Review.builder()
                .content(content)
                .point(point)
                .userId(tokenInfoResponse.id())
                .userName(tokenInfoResponse.nickname())
                .build();
    }
}
