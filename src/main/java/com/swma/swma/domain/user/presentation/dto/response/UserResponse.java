package com.swma.swma.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

	private final Long id;
	private final int certifyDate;
	private final String img;
	private final String name;
	private final String sex;
	private final int age;
	private final String description;
	private final String live;

	@Builder
	public UserResponse(Long id, int certifyDate, String img, String name, String sex, int age, String description, String country, String region) {
		this.id = id;
		this.certifyDate = certifyDate;
		this.img = img;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.description = description;
		this.live = country+", "+region;
	}

}
