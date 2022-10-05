package com.swma.swma.domain.account.service;

import com.swma.swma.domain.account.exception.UserAlreadyExistException;
import com.swma.swma.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IdVerityService {
    private final UserRepository userRepository;

    public void execute(String userId){
        if(userRepository.existsUserByUserId(userId)){
            throw new UserAlreadyExistException();
        }
    }
}
