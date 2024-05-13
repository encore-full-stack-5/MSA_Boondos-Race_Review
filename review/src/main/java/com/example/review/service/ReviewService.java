package com.example.review.service;

import com.example.review.dto.request.ReviewRequest;
import com.example.review.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

  void createReview(String token, ReviewRequest request);

  void updateReview(String token, Long reviewId , ReviewRequest request);

  void deleteReview(String token, Long reviewId);

  List<ReviewResponse> getAllReviews(Long productId);


}
