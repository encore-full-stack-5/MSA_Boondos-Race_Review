package com.example.review.global;

import com.example.review.dto.response.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiAuth {
    private final FeignAuth feignAuth;

    public TokenInfoResponse parseToken(String token) {
        return feignAuth.parseToken(token);
    }
}