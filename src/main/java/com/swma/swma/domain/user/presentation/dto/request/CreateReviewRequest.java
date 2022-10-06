package com.swma.swma.domain.user.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateReviewRequest {
    private final int star;
    private final String message;
    private final Long userId;
}
