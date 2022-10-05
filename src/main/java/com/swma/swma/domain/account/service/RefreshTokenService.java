package com.swma.swma.domain.account.service;

import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.utils.GeneratedToken;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;
import com.swma.swma.global.security.exception.TokenNotValidException;
import com.swma.swma.global.security.jwt.TokenProvider;
import com.swma.swma.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final GeneratedToken generatedToken;

    public TokenResponse execute(String refreshToken){
        String userId = tokenProvider.getUserEmail(refreshToken, jwtProperties.getRefreshSecret());
        User user = userRepository.findUserByUserId(userId).orElseThrow(()-> UserNotFoundException.EXCEPTION);
        if(!user.getRefreshToken().equals(refreshToken)) {
            throw new TokenNotValidException();
        }
        TokenResponse tokenData = generatedToken.execute(userId);
        user.updateRefreshToken(tokenData.getRefreshToken());
        return tokenData;
    }
}
