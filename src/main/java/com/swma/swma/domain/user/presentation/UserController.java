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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return userService.getMainpage(page, userUtils.currentUser());
	}

	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@GetMapping
	public UserResponse getMyUser() {
		return userService.getUserList(userUtils.currentUser());
	}

	@PatchMapping("/ip")
	public void updateCountryOrRegion(@RequestBody @Valid LiveRequest request) {
		userService.updateCountryOrRegion(userUtils.currentUser(), request);
	}

	@PatchMapping
	public void updateUser(@RequestBody @Valid UserRequest request) {
		userService.update(userUtils.currentUser(), request);
	}

	@GetMapping("/search")
	public MainPageResponse userSearchCountry(@PageableDefault Pageable page, @RequestParam("country")String country) {
		return userService.userSearchCountry(page, country, userUtils.currentUser());
	}

}
