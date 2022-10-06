package com.swma.swma.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ReviewResponse {
    private final Long id;
    private final String reviewerImg;
    private final String message;
    private final int star;
    private final Long userId;
}
