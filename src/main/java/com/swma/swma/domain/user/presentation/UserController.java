package com.swma.swma.domain.user.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swma.swma.domain.user.presentation.dto.response.MainPageResponse;
import com.swma.swma.domain.user.presentation.dto.response.UserResponse;
import com.swma.swma.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

	public final UserService userService;

	@GetMapping("/main")
	public MainPageResponse getMain(@PageableDefault Pageable page) {
		Long userId = 1L;
		return userService.getUserProfile(page, userId);
	}

}
