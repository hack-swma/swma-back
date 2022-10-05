package com.swma.swma.domain.user.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.presentation.dto.response.MainPageResponse;
import com.swma.swma.domain.user.presentation.dto.response.UserResponse;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public MainPageResponse getUserProfile(Pageable page, Long userId) {
		Page<User> users = userRepository.findAllByOrderByIdDesc(page);
		return MainPageResponse.builder()
			.totalPages(users.getTotalPages())
			.img(userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION).getImg())
			.userResponses(users.stream().map(this::ofUserResponse).collect(Collectors.toList()))
			.build();
	}

	public UserResponse getUser(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> UserNotFoundException.EXCEPTION);
		LocalDate nowDate = LocalDate.now();

		int age = nowDate.getYear() - user.getDate().getYear() -
			(!(user.getDate().getMonthValue() < nowDate.getMonthValue() ||
				(user.getDate().getMonth() == nowDate.getMonth() &&
					user.getDate().getDayOfMonth()<= nowDate.getDayOfMonth())) ? 1 : 0);

		int certifyDate;
		if(nowDate.getYear()-user.getDate().getYear()!=0) {
			certifyDate = (365*(nowDate.getYear()-user.getDate().getYear()))+user.getDate().getDayOfYear();
		} else {
			certifyDate = nowDate.getDayOfYear()-user.getDate().getDayOfYear();
		}

		return UserResponse.builder()
			.certifyDate(certifyDate)
			.age(age)
			.sex(user.getSex().getSex())
			.country(user.getCountry())
			.region(user.getRegion())
			.img(user.getImg())
			.name(user.getName())
			.description(user.getDescription())
			.build();
	}

	private MainPageResponse.UserResponse ofUserResponse(User user) {
		return MainPageResponse.UserResponse.builder()
			.name(user.getName())
			.country(user.getCountry())
			.region(user.getRegion())
			.img(user.getImg())
			.build();
	}


}
