package com.swma.swma.domain.account.service;

import org.springframework.stereotype.Service;

import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.UserUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService {
	private final UserUtils userUtils;
	private final UserRepository userRepository;

	public void execute() {
		User user = userUtils.currentUser();
		userRepository.save(user.updateRefreshToken(null));
	}

}
