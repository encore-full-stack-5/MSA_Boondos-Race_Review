package com.example.review.domain;

import com.example.review.global.JsonStringListConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
    private Long reviewId;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "POINT")
    @Setter
    private int point;

    @Column(name = "CONTENT")
    @Setter
    private String content;

    @Column(name = "CREATED_AT")
    @CreatedDate
    @UpdateTimestamp
    private LocalDateTime createdAt;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "images")
    @Convert(converter = JsonStringListConverter.class)
    @Setter
    private List<String> images;
}
