package com.swma.swma.domain.account.presentation.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
}