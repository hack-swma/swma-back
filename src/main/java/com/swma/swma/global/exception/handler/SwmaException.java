package com.swma.swma.global.exception.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SwmaException extends RuntimeException {

	private final ErrorCode errorCode;

}
