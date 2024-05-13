package com.example.review.controller;

import com.example.review.dto.request.ReviewRequest;
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
    public void createReview(@RequestHeader String token
            ,ReviewRequest request){
        reviewService.createReview(token,request);
    }

    @PatchMapping("/{ReviewId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateReview(@RequestHeader String token, @PathVariable Long ReviewId, ReviewRequest request){
        reviewService.updateReview(token,ReviewId,request);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReviewResponse> getAllReviews(@PathVariable Long productId){
        return reviewService.getAllReviews(productId);
    }

    @DeleteMapping("/my/{reviewId}")
    public void deleteReview(@RequestHeader String token,@PathVariable Long reviewId){
        reviewService.deleteReview(token,reviewId);
    }
}
