package com.swma.swma.global.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	USER_NOT_FOUND(404, "유저를 찾을 수 없습니다.");

	private final int status;
	private final String message;

}
