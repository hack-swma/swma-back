package com.swma.swma.domain.user.presentation;

import javax.validation.Valid;

import com.swma.swma.domain.user.presentation.dto.request.CreateReviewRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.swma.swma.domain.user.presentation.dto.request.UserRequest;
import com.swma.swma.domain.user.presentation.dto.response.MainPageResponse;
import com.swma.swma.domain.user.presentation.dto.response.UserResponse;
import com.swma.swma.domain.user.service.UserService;

import com.swma.swma.domain.user.presentation.dto.request.LiveRequest;
import com.swma.swma.global.UserUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

	private final UserUtils userUtils;
	private final UserService userService;

	@GetMapping("/main")
	public MainPageResponse getMain(@PageableDefault Pageable page) {
		return userService.getMainPage(page, userUtils.currentUser());
	}

	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@GetMapping
	public UserResponse getMyUser() {
		return userService.getUserList(userUtils.currentUser());
	}
	@PostMapping("/review")
	public void createReview(@RequestBody CreateReviewRequest createReviewRequest){
		userService.createReview(createReviewRequest);
	}

	@PatchMapping("/ip")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCountryOrRegion(@RequestBody @Valid LiveRequest request) {
		userService.updateCountryOrRegion(userUtils.currentUser(), request);
	}

	@PatchMapping
	public Long updateUser(@RequestBody @Valid UserRequest request) {
		return userService.update(userUtils.currentUser(), request);
	}

	@GetMapping("/search")
	public MainPageResponse userSearchCountry(@PageableDefault Pageable page, @RequestParam("country")String country) {
		return userService.userSearchCountry(page, country, userUtils.currentUser());
	}

}
