package com.swma.swma.domain.account.utils;

import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.global.security.jwt.TokenProvider;
import com.swma.swma.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratedToken {
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(String userId){
        String accessToken = tokenProvider.generatedAccessToken(userId);
        String refreshToken = tokenProvider.generatedRefreshToken(userId);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
