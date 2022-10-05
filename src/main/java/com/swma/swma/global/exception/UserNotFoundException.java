package com.swma.swma.global.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class UserNotFoundException extends SwmaException {

	public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}

}
