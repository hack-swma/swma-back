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

	public MainPageResponse getUserProfile(Pageable page, String userId) {
		Page<User> users = userRepository.findAllByOrderByIdDesc(page);
		return MainPageResponse.builder()
			.totalPages(users.getTotalPages())
			.img(userRepository.findUserByUserId(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION).getImg())
			.userResponses(users.stream().map(this::ofUserResponse).collect(Collectors.toList()))
			.build();
	}

	public UserResponse getUser(Long userId) {
		return getUserList(userRepository.findUserById(userId)
			.orElseThrow(() -> UserNotFoundException.EXCEPTION));
	}

	public UserResponse getUserList(User user) {
		LocalDate nowDate = LocalDate.now();

		int age = nowDate.getYear() - user.getDate().getYear() -
			(!(user.getDate().getMonthValue() < nowDate.getMonthValue() ||
				(user.getDate().getMonth() == nowDate.getMonth() &&
					user.getDate().getDayOfMonth()<= nowDate.getDayOfMonth())) ? 1 : 0);

		return UserResponse.builder()
			.id(user.getId())
			.certifyDate(user.getCertifyDate())
			.age(age)
			.sex(user.getSex().getSex())
			.country(user.getCountry())
			.region(user.getRegion())
			.img(user.getImg())
			.name(user.getName())
			.description(user.getDescription())
			.build();
	}

	@Transactional
	public void updateCountryOrRegion(User user, LiveRequest request) {
		user.updateCountryOrRegion(request.getCountry(), request.getRegion());
		userRepository.save(user)
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
