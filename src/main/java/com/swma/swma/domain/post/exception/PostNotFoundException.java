package com.swma.swma.domain.post.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class PostNotFoundException extends SwmaException {
    public static final PostNotFoundException EXCEPTION = new PostNotFoundException();

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
