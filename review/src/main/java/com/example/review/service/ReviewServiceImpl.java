package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.dto.request.ReviewRequest;
import com.example.review.dto.response.ReviewResponse;
import com.example.review.dto.response.TokenInfoResponse;
import com.example.review.global.ApiAuth;
import com.example.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ApiAuth apiAuth;
    @Override
    public void createReview(String token, ReviewRequest request) {
        TokenInfoResponse parseToken = apiAuth.parseToken(token);
        reviewRepository.save(request.toEntity(parseToken));
    }

    @Override
    @Transactional
    public void updateReview(String token,Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("업데이트 실패"));
        review.setContent(request.getContent());
        review.setPoint(request.getPoint());
    }

    @Override
    public void deleteReview(String token,Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewResponse> getAllReviews(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(ReviewResponse::from).toList();
    }

}
