package com.swma.swma.domain.account.service;

import com.swma.swma.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class IdVerityService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void execute(String userId){
        if(userRepository.existsUserByUserId(userId)){
            //에러
        }

    }
}
