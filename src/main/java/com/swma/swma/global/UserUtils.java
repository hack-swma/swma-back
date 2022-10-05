package com.swma.swma.global;

import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;

    public User currentUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUserId(userId).orElseThrow(()-> new UserNotFoundException());
    }
}
