package com.swma.swma.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ReviewResponse {
    private Long id;
    private String reviewerImg;
    private String message;
    private int star;
    private Long userId;
}
