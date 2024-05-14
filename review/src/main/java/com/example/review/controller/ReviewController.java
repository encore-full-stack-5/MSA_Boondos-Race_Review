package com.example.review.controller;

import com.example.review.dto.request.ReviewRequest;
import com.example.review.dto.request.UpdateRequest;
import com.example.review.dto.response.ReviewResponse;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createReview(@RequestHeader("Authorization") String token
                            ,@RequestBody ReviewRequest request){
        reviewService.createReview(token.substring(7),request);
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

    @DeleteMapping("/my/{reviewId}")
    public void deleteReview(@RequestHeader("Authorization") String token,@PathVariable Long reviewId){
        reviewService.deleteReview(token.substring(7),reviewId);
    }
}
