package com.swma.swma.domain.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.swma.swma.domain.user.entity.Review;
import com.swma.swma.domain.user.presentation.dto.request.CreateReviewRequest;
import com.swma.swma.domain.user.presentation.dto.response.ReviewResponse;
import com.swma.swma.domain.user.repository.ReviewRepository;
import com.swma.swma.global.UserUtils;
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
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final UserUtils userUtils;

	public MainPageResponse getMainPage(Pageable page, User user) {
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
		return UserResponse.builder()
			.userId(user.getUserId())
			.certifyDate(user.getCertifyDate())
			.age(user.ofAge(LocalDate.now()))
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

		languageRepository.deleteAll(languageRepository.findAllByUserId(user.getId()));

		List<Language> languages = new ArrayList<>();
		for(LanguageType languageType : request.getLanguage()) {
			languages.add(new Language(languageType, user));
		}
		languageRepository.saveAll(languages);
		user.update(request.getName(), request.getDescription(), request.getImg());
		return userRepository.save(user).getId();
	}
	@Transactional
	public void createReview(CreateReviewRequest createReviewRequest){
		User user = userUtils.currentUser();
		reviewRepository.save(Review.builder()
				.reviewerImg(user.getImg())
				.userId(createReviewRequest.getUserId())
				.message(createReviewRequest.getMessage())
				.star(createReviewRequest.getStar())
				.build());
	}

	private List<ReviewResponse> getReviewList(Long userId){
		List<Review> reviewList = reviewRepository.findByUserId(userId);
		List<ReviewResponse> reviewResponses = new ArrayList<>();
		reviewList.forEach(review->{
			reviewResponses.add(ReviewResponse.builder()
					.id(review.getId())
					.reviewerImg(review.getReviewerImg())
					.star(review.getStar())
					.message(review.getMessage())
					.userId(review.getUserId())
					.build());
		});
		return reviewResponses;
	}
	private int getAvgReviews(Long userId){
		float ret = 0;
		List<Review> reviewList = reviewRepository.findByUserId(userId);
		for(Review review:reviewList) {
			ret+=review.getStar();
		}
		return (int) (ret/reviewList.size());
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
