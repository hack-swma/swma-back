package com.swma.swma.domain.account.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class PasswordWrongException extends SwmaException {
    public PasswordWrongException() {
        super(ErrorCode.PASSWORD_WRONG);
    }
}
