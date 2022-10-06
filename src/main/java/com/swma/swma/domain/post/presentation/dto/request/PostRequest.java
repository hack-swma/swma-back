package com.swma.swma.domain.post.presentation.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRequest {

	@NotBlank
	@Size(min = 4, max = 30)
	private String title;

	@NotBlank
	@Size(min = 10, max = 1000)
	private String content;

	private  int member;

	private LocalDate startDate;
	private LocalDate endDate;

	@Builder
	public PostRequest(String title, String content, int member, LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
