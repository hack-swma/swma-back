package com.swma.swma.domain.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swma.swma.domain.user.entity.Language;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.entity.type.LanguageType;
import com.swma.swma.domain.user.presentation.dto.request.LiveRequest;
import com.swma.swma.domain.user.presentation.dto.request.UserRequest;
import com.swma.swma.domain.user.presentation.dto.response.MainPageResponse;
import com.swma.swma.domain.user.presentation.dto.response.UserResponse;
import com.swma.swma.domain.user.repository.LanguageRepository;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final LanguageRepository languageRepository;
	private final UserRepository userRepository;

	public MainPageResponse getMainpage(Pageable page, User user) {
		Page<User> users = userRepository.findAllByOrderByIdDesc(page);
		return getUserProfile(users, user);
	}

	public MainPageResponse userSearchCountry(Pageable page, String country, User user) {
		Page<User> users = userRepository.findAllByCountryOrderByIdDesc(country, page);
		return getUserProfile(users,user);
	}

	private MainPageResponse getUserProfile(Page<User> users, User user) {
		return MainPageResponse.builder()
			.totalPages(users.getTotalPages())
			.img(user.getImg())
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
		userRepository.save(user);
	}

	@Transactional
	public Long update(User user, UserRequest request) {
		user.update(request.getName(), request.getDescription(), request.getImg());

		languageRepository.deleteAll(user.getLanguages());
		List<Language> languages = new ArrayList<>();
		for(LanguageType languageType : request.getLanguage()) {
			languages.add(new Language(languageType, user));
		}

		languageRepository.saveAll(languages);
		return userRepository.save(user).getId();
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
