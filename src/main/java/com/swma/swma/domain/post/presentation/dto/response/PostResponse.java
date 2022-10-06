package com.swma.swma.domain.post.presentation.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

	private final Long id;
	private final String name;
	private final String img;

	private final String live;
	private final String date;
	private final int member;

	private final String title;
	private final String content;

	@Builder
	public PostResponse(Long id, String title, LocalDate startDate, LocalDate endDate, String img, String name, String country, String region, String content, int member) {
		this.id = id;
		this.title = title;
		this.date = startDate.toString() + " ~ " + endDate.toString();
		this.img = img;
		this.name = name;
		this.live = country + ", " + region;
		this.content = content;
		this.member = member;
	}

}
