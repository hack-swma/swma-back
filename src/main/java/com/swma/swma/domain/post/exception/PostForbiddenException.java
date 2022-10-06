package com.swma.swma.domain.post.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class PostForbiddenException extends SwmaException {

	public static final PostForbiddenException EXCEPTION = new PostForbiddenException();

	public PostForbiddenException() {
		super(ErrorCode.POST_NOT_FOUND);
	}

}
