package com.swma.swma.domain.account.service;

import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratedTokenService {
    private final TokenProvider tokenProvider;

    public TokenResponse execute(String userId){
        String accessToken = tokenProvider.generateAccessToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken(userId);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
