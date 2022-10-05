package com.swma.swma.domain.account.service;

import com.swma.swma.domain.account.exception.PasswordWrongException;
import com.swma.swma.domain.account.presentation.dto.request.SignInRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.utils.GeneratedToken;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final GeneratedToken generatedToken;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignInRequest signInRequest){
        User user = userRepository.findUserByUserId(signInRequest.getId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new PasswordWrongException();
        }
        TokenResponse tokenData = generatedToken.execute(signInRequest.getId());
        user.updateRefreshToken(tokenData.getRefreshToken());
        return tokenData;
    }
}
