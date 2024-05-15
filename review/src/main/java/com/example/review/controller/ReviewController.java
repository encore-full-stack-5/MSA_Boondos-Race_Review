package com.example.review.controller;

import com.example.review.domain.ReviewProducer;
import com.example.review.dto.KafkaStatus;
import com.example.review.dto.request.KafkaRequest;
import com.example.review.dto.request.ReviewRequest;
import com.example.review.dto.request.UpdateRequest;
import com.example.review.dto.response.ReviewResponse;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewProducer reviewProducer;
    private final List<KafkaStatus<KafkaRequest>> list = new ArrayList<>();
    @PostMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createReview(@RequestHeader("Authorization") String token
                            ,@RequestBody ReviewRequest request
                            ,@PathVariable Long productId){

        KafkaRequest kafkaRequest = new KafkaRequest();
        kafkaRequest.setProductId(productId);
        kafkaRequest.setCheck(true);

        KafkaStatus<KafkaRequest> reviewKafkaStatus = new KafkaStatus<>(kafkaRequest, "insert");
        list.add(reviewKafkaStatus);
        reviewProducer.send(kafkaRequest,"insert");
        list.remove(reviewKafkaStatus);
        reviewService.createReview(token.substring(7),request,productId);
    }

    @PutMapping("/{ReviewId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateReview(@RequestHeader("Authorization") String token,
                             @PathVariable Long ReviewId,
                             @RequestBody UpdateRequest request){
        reviewService.updateReview(token.substring(7),ReviewId,request);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReviewResponse> getAllReviews(@PathVariable Long productId){
        return reviewService.getAllReviews(productId);
    }

    @GetMapping("/my/written-reviews")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReviewResponse> getMyReviews(@RequestHeader("Authorization") String token){
        return reviewService.getMyReviews(token);
    }

    @DeleteMapping("/my/{reviewId}")
    public void deleteReview(@RequestHeader("Authorization") String token,@PathVariable Long reviewId){
        reviewService.deleteReview(token.substring(7),reviewId);
    }
}
