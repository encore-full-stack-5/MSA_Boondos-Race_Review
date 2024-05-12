package com.example.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    Long reviewId;

    @Column(name = "ORDER_ID")
    Long orderId;

    @Column(name = "USER_ID")
    UUID userId;

    @Column(name = "PRODUCT_ID")
    Long productId;

    @Column(name = "POINT")
    int point;

    @Column(name = "CONTENT")
    String content;

    @Column(name = "CREATED_AT")
    LocalDateTime createdAt;

    @ElementCollection
    @Column(name = "PRODUCT_OPTION")
    List<String> productOption;

    @Column(name = "SELLER_NAME")
    String sellerName;

    @Column(name = "USER_NAME")
    String userName;

}
