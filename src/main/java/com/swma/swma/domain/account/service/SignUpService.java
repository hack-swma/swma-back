package com.swma.swma.domain.account.service;

import com.swma.swma.domain.account.exception.UserAlreadyExistException;
import com.swma.swma.domain.account.presentation.dto.request.SignUpRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final GeneratedTokenService generatedTokenService;

    @Transactional
    public TokenResponse execute(SignUpRequest authenticationRequest){
        if(userRepository.existsUserByUserId(authenticationRequest.getId())) throw new UserAlreadyExistException();
        TokenResponse tokenData = generatedTokenService.execute(authenticationRequest.getId());
        User user = User.builder()
                .userId(authenticationRequest.getId())
                .password(authenticationRequest.getPassword())
                .refreshToken(tokenData.getRefreshToken())
                .build();
        userRepository.save(user);
        return tokenData;
    }
}
