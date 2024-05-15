package com.example.review.dto.request;

import com.example.review.domain.Review;
import com.example.review.dto.response.TokenInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private int point;
    private String content;
    private List<String> images;


    public Review toEntity(TokenInfoResponse tokenInfoResponse, Long productId){
        return Review.builder()
                .content(content)
                .point(point)
                .images(images)
                .userId(tokenInfoResponse.id())
                .userName(tokenInfoResponse.nickname())
                .productId(productId)
                .build();
    }
}
