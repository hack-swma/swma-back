package com.swma.swma.domain.account.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swma.swma.domain.account.exception.UserAlreadyExistException;
import com.swma.swma.domain.account.presentation.dto.request.SignUpRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.utils.GeneratedToken;
import com.swma.swma.domain.user.entity.Language;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.entity.type.LanguageType;
import com.swma.swma.domain.user.entity.type.Sex;
import com.swma.swma.domain.user.repository.LanguageRepository;
import com.swma.swma.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final GeneratedToken generatedToken;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignUpRequest request){
        if(userRepository.existsUserByUserId(request.getId())) throw new UserAlreadyExistException();

        TokenResponse tokenData = generatedToken.execute(request.getId());
        LocalDate date = request.validDate();

        User user = userRepository.save(User.builder()
            .userId(request.getId())
            .password(passwordEncoder.encode(request.getPassword()))
            .name(request.getName())
            .date(date)
            .sex(request.getSex())
            .img(request.getImg())
            .description(request.getDescription())
            .country(request.getCountry())
            .region(request.getRegion())
            .refreshToken(tokenData.getRefreshToken())
            .build());

        List<Language> languages = new ArrayList<>();
        for(LanguageType languageType : request.getLanguage()) {
            languages.add(new Language(languageType, user));
        }
        languageRepository.saveAll(languages);

        return tokenData;
    }
}
