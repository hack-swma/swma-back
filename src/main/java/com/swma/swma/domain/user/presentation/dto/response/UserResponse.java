package com.swma.swma.domain.user.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.swma.swma.domain.user.entity.Review;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class UserResponse {

	private final String userId;
	private final LocalDate certifyDate;
	private final String img;
	private final String name;
	private final String sex;
	private final int age;
	private final String description;
	private final String live;
	private final List<String> languages;
	private final List<ReviewResponse> reviews;

	@Builder
	public UserResponse(String userId, LocalDate certifyDate, String img, String name, String sex, int age, String description, String country, String region, List<String> languages, List<ReviewResponse> reviews) {
		this.userId = userId;
		this.certifyDate = certifyDate;
		this.img = img;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.description = description;
		this.reviews = reviews;
		this.live = country+", "+region;
		this.languages = languages;
	}
}
