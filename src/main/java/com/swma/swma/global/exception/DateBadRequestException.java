package com.swma.swma.global.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class DateBadRequestException extends SwmaException {

	public static final DateBadRequestException Exception = new DateBadRequestException();

	public DateBadRequestException() {
		super(ErrorCode.DATE_BAD_REQUEST);
	}

}
