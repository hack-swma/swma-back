package com.swma.swma.domain.user.presentation;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return userService.getUserProfile(page, userUtils.currentUserId());
	}

	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@GetMapping
	public UserResponse getMyUser() {
		return userService.getUserList(userUtils.currentUser());
		return userService.getUser(userId);
	@PatchMapping("/ip")
	public void updateCountryOrRegion(@RequestBody @Valid LiveRequest request) {
		userService.updateCountryOrRegion(userUtils.currentUser(), request);
	}

}
