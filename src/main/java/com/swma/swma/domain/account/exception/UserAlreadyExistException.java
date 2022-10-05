package com.swma.swma.domain.account.exception;

import com.swma.swma.global.exception.UserNotFoundException;
import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class UserAlreadyExistException extends SwmaException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    public UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXIST);
    }
}
