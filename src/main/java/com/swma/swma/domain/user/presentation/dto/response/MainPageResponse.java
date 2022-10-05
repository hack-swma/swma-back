package com.swma.swma.domain.user.presentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainPageResponse {

	private final int totalPages;
	private final String img;
	private final List<UserResponse> userResponses;

	@Builder
	public MainPageResponse(int totalPages, String img, List<UserResponse> userResponses) {
		this.totalPages = totalPages;
		this.img = img;
		this.userResponses = userResponses;
	}

	@Getter
	public static class UserResponse {
		private final String name;
		private final String live;
		private final String img;

		@Builder
		public UserResponse(String name, String country, String region, String img) {
			this.name = name;
			this.live = country+", "+region;
			this.img = img;
		}

	}


}
