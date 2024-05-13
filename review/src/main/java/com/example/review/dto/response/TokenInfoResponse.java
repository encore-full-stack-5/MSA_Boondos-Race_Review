package com.example.review.dto.response;

import java.util.UUID;

public record TokenInfoResponse (
        UUID id,
        String nickname
){
}