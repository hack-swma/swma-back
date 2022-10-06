package com.swma.swma.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class LiveRequest {

	@NotBlank
	private String country;

	@NotBlank
	private String region;

	public LiveRequest(String country, String region) {
		this.country = country;
		this.region = region;
	}
}
