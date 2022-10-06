package com.swma.swma.domain.post.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainPageResponse {

	private final int totalPages;
	private final String img;
	private List<PostResponse> postResponses;

	@Builder
	public MainPageResponse(int totalPages, String img, List<PostResponse> postResponses) {
		this.totalPages = totalPages;
		this.img = img;
		this.postResponses = postResponses;
	}

	@Getter
	public static class PostResponse {

		private final Long postId;
		private final String title;

		private final String img;
		private final String name;

		private final String date;
		private final int member;
		private final String live;

		@Builder
		public PostResponse(Long postId, String title, LocalDate startDate, LocalDate endDate, int member, String img, String name, String country, String region) {
			this.postId = postId;
			this.title = title;
			this.date = startDate.toString() + " ~ " + endDate.toString();
			this.member = member;
			this.img = img;
			this.name = name;
			this.live = country + ", " + region;
		}

	}

}
