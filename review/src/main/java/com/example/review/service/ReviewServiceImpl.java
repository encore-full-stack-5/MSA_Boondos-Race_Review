package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.dto.request.ReviewRequest;
import com.example.review.dto.request.UpdateRequest;
import com.example.review.dto.response.ReviewResponse;
import com.example.review.dto.response.TokenInfoResponse;
import com.example.review.global.ApiAuth;
import com.example.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ApiAuth apiAuth;

    @Override
    public void createReview(String token, ReviewRequest request ,Long productId) {
        TokenInfoResponse parseToken = apiAuth.parseToken(token);//토큰으로 부터 받아오는 아이디
        reviewRepository.save(request.toEntity(parseToken,productId));
    }

    @Override
    @Transactional
    public void updateReview(String token,Long reviewId, UpdateRequest request) {
        TokenInfoResponse parseToken = apiAuth.parseToken(token);//토큰으로 부터 받아오는 아이디

        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new IllegalArgumentException("업데이트 실패"));

        if(!review.getUserId().equals(parseToken.id())) throw new IllegalArgumentException("잘못된 요청 입니다.");

        review.setContent(request.getContent());
        review.setPoint(request.getPoint());
        review.setImages(request.getImages());
    }

    @Override
    public void deleteReview(String token,Long reviewId) {
        TokenInfoResponse parseToken = apiAuth.parseToken(token);//토큰으로 부터 받아오는 아이디
       Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new IllegalArgumentException("해당하는 리뷰가 존재하지 않습니다"));
       if(!review.getUserId().equals(parseToken.id())) throw new IllegalArgumentException("잘못된 요청 입니다.");

       reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewResponse> getAllReviews(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(ReviewResponse::from).toList();
    }

    @Override
    public List<ReviewResponse> getMyReviews(String token) {
        TokenInfoResponse parseToken = apiAuth.parseToken(token);//토큰으로 부터 받아오는 아이디
        if(parseToken == null) throw  new IllegalArgumentException("토큰 정보 없음");
        List<Review>reviews = reviewRepository.findAllByUserId(parseToken.id());
        return reviews.stream().map(ReviewResponse::from).toList();
    }


}
