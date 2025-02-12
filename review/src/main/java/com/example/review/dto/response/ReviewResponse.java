package com.example.review.dto.response;

import com.example.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewResponse {
   private String content;
   private int point;
   private String username;
   private List<String> options;
   private LocalDateTime createdAt;

   public static ReviewResponse from(Review review){
       return ReviewResponse.builder()
               .content(review.getContent())
               .point(review.getPoint())
               .username(review.getUserName())
               .createdAt(review.getCreatedAt())
               .build();
   }
}
